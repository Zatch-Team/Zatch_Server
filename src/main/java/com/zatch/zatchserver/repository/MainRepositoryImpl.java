package com.zatch.zatchserver.repository;

import com.zatch.zatchserver.domain.ViewNearZatch;
import com.zatch.zatchserver.domain.ViewPopularZatch;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MainRepositoryImpl implements MainRepository {
    private final JdbcTemplate jdbcTemplate;

    public MainRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<ViewNearZatch> getNearZatch(Long userId) {
        List<ViewNearZatch> results = jdbcTemplate.query(
                "SELECT user.user_id, zatch.zatch_id, zatch.category_id, zatch.is_free, zatch.item_name, zatch.content, zatch.updated_at, quantity, purchase_date, expiration_date, is_opened, allow_any_zatch, zatch.like_count, user.town1, user.town2, user.town3, zatch_img.zatch_img_url FROM zatch LEFT OUTER JOIN zatch_img ON zatch.zatch_id = zatch_img.zatch_id JOIN user ON zatch.user_id = user.user_id WHERE ((town1 IN (SELECT town1 or town2 or town3 FROM user)) or (town2 IN (SELECT town1 or town2 or town3 FROM user)) or (town3 IN (SELECT town1 or town2 or town3 FROM user))) and user.user_id != ?;",
                new RowMapper<ViewNearZatch>() {
                    @Override
                    public ViewNearZatch mapRow(ResultSet rs, int rowNum) throws SQLException {
                        ViewNearZatch nearZatch = new ViewNearZatch(
                                rs.getLong("category_id"),
                                rs.getBoolean("is_free"),
                                rs.getString("item_name"),
                                rs.getString("content"),
                                rs.getString("zatch_img_url"),
                                rs.getInt("quantity"),
                                rs.getDate("purchase_date"),
                                rs.getDate("expiration_date"),
                                rs.getDate("updated_at"),
                                rs.getInt("is_opened"),
                                rs.getInt("allow_any_zatch"),
                                rs.getInt("like_count"),
                                rs.getString("town1"),
                                rs.getString("town2"),
                                rs.getString("town3")
                        );
                        return nearZatch;
                    }
                }, userId);
        return results.isEmpty() ? null : results;
    }

    @Override
    public List<ViewPopularZatch> getPopularZatch(Long userId){
        List<ViewPopularZatch> results = jdbcTemplate.query(
                "SELECT user_id, zatch_id, category_id, is_free, md_name, content, updated_at, quantity, date_buy, date_expire, is_opened, any_zatch, like_count FROM zatch ORDER BY like_count DESC LIMIT 3;",
                new RowMapper<ViewPopularZatch>() {
                    @Override
                    public ViewPopularZatch mapRow(ResultSet rs, int rowNum) throws SQLException {
                        ViewPopularZatch popularZatch = new ViewPopularZatch(
                                rs.getLong("category_id"),
                                rs.getBoolean("is_free"),
                                rs.getString("md_name"),
                                rs.getString("content"),
                                rs.getInt("quantity"),
                                rs.getDate("date_buy"),
                                rs.getDate("date_expire"),
                                rs.getDate("updated_at"),
                                rs.getInt("is_opened"),
                                rs.getInt("any_zatch"),
                                rs.getInt("like_count")
                        );
                        return popularZatch;
                    }
                });
        return results.isEmpty() ? null : results;
    }
}
