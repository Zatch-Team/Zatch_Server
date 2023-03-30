package com.zatch.zatchserver.repository;

import com.zatch.zatchserver.domain.ViewNearZatch;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MainRepositoryImpl implements MainRepository{
    private final JdbcTemplate jdbcTemplate;

    public MainRepositoryImpl(DataSource dataSource) {this.jdbcTemplate = new JdbcTemplate(dataSource);}

    @Override
    public List<ViewNearZatch> getNearZatch(Long userId){
        List<ViewNearZatch> results = jdbcTemplate.query(
                "SELECT user.user_id, zatch.zatch_id, zatch.category_id, zatch.is_free, zatch.item_name, zatch.content, quantity, purchase_date, expiration_date, is_opened, allow_any_zatch, zatch.like_count, user.town1, user.town2, user.town3, zatch_img.zatch_img_url FROM zatch join zatch_img on zatch.zatch_id = zacth_img.zatch_id join user on zatch.user_id = user.user_id where (town1 = (select town1, town2, town3 from user where user.user_id = ?)) or (town2 = (select town1, town2, town3 from user where user.user_id = ?)) or (town3 = (select town1, town2, town3 from user where user.user_id = ?))",
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
                }, userId, userId, userId);
        return results.isEmpty() ? null : results;
    }
}
