package com.zatch.zatchserver.repository;

import com.zatch.zatchserver.domain.Address;
import com.zatch.zatchserver.domain.ViewNearZatch;
import com.zatch.zatchserver.domain.ViewPopularZatch;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MainRepositoryImpl implements MainRepository {
    private final JdbcTemplate jdbcTemplate;

    public MainRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public String getMainTown(Long userId) {
        String sql = "SELECT main_addr_name FROM user WHERE user_id = ?;";
        Object[] params = {userId};
        String main_addr_name = String.valueOf(jdbcTemplate.queryForList(sql, params).get(0).get("main_addr_name"));
        return main_addr_name;
    }

    @Override
    public List<ViewNearZatch> getNearZatch(Long userId) {
        String sql = "SELECT main_addr_x, main_addr_y FROM user WHERE user_id = ?;";
        Object[] params = {userId};
        Double main_addr_x = Double.valueOf(String.valueOf(jdbcTemplate.queryForList(sql, params).get(0).get("main_addr_x")));
        Double main_addr_y = Double.valueOf(String.valueOf(jdbcTemplate.queryForList(sql, params).get(0).get("main_addr_y")));
        Double dist;
        List<Long> dist_suc = new ArrayList<>();

        List<Address> addresses = jdbcTemplate.query(
                "SELECT user_id, main_addr_name, main_addr_x, main_addr_y FROM user WHERE user_id in (SELECT user_id FROM zatch);",
                new RowMapper<Address>() {
                    @Override
                    public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Address address = new Address(
                                rs.getLong("user_id"),
                                rs.getString("main_addr_name"),
                                String.valueOf(rs.getDouble("main_addr_x")),
                                String.valueOf(rs.getDouble("main_addr_y"))
                        );
                        return address;
                    }
                });

        for (int i = 0; i < addresses.size(); i++) {
            System.out.println(addresses.get(0));
            Double theta = Double.parseDouble(addresses.get(i).getAddr_y()) - main_addr_y;
            dist = Math.sin(Double.parseDouble(addresses.get(i).getAddr_x()) * Math.PI / 180.0) * Math.sin(main_addr_x * Math.PI / 180.0) + Math.cos(Double.parseDouble(addresses.get(i).getAddr_y()) * Math.PI / 180.0) * Math.cos(main_addr_y * Math.PI / 180.0) * Math.cos(theta * Math.PI / 180.0);

            dist = Math.acos(dist);
            dist = dist * 180 / Math.PI;
            dist = dist * 60 * 1.1515 * 1.609344;
            if(dist <= 1.0){
                dist_suc.add(addresses.get(i).getUser_id());
            }
        }
        System.out.println(dist_suc);


        List<ViewNearZatch> results = jdbcTemplate.query(
                "SELECT zatch.user_id, zatch_id, category_id, is_free, md_name, content, zatch.updated_at, quantity, date_buy, date_expire, is_opened, any_zatch, like_count, main_addr_name FROM zatch JOIN user on zatch.user_id = user.user_id;",
                new RowMapper<ViewNearZatch>() {
                    @Override
                    public ViewNearZatch mapRow(ResultSet rs, int rowNum) throws SQLException {
                        ViewNearZatch nearZatch = new ViewNearZatch(
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
                                rs.getInt("like_count"),
                                rs.getString("main_addr_name")
                        );
                        return nearZatch;
                    }
                });
        return results.isEmpty() ? null : results;
    }

    @Override
    public List<ViewPopularZatch> getPopularZatch(Long userId) {
        List<ViewPopularZatch> results = jdbcTemplate.query(
                "SELECT user_id, zatch_id, category_id, is_free, md_name, content, updated_at, quantity, date_buy, date_expire, is_opened, any_zatch, like_count FROM zatch ORDER BY like_count DESC LIMIT 3;",
                new RowMapper<ViewPopularZatch>() {
                    @Override
                    public ViewPopularZatch mapRow(ResultSet rs, int rowNum) throws SQLException {
                        ViewPopularZatch popularZatch = new ViewPopularZatch(
                                rs.getLong("zatch_id"),
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
