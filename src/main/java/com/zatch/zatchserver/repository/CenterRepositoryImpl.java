package com.zatch.zatchserver.repository;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Repository
public class CenterRepositoryImpl implements CenterRepository{

    private final JdbcTemplate jdbcTemplate;

    public CenterRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Map<String, Object>> getList(Long menu) {
        try {
            String sql = "SELECT user_center_title, user_center_content FROM user_center WHERE user_center_menu = ?";
            Object[] params = {menu};
            return jdbcTemplate.queryForList(sql, params);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Get Center Menu List Error");
        }
    }
}
