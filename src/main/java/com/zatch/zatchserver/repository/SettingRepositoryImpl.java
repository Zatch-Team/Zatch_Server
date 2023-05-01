package com.zatch.zatchserver.repository;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public class SettingRepositoryImpl implements SettingRepository {
    private final JdbcTemplate jdbcTemplate;

    public SettingRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Boolean postAlertAgree(Long userId, Boolean alertAgree) {
        try {
            String sql = "UPDATE user SET alert_agree = ? WHERE user_id = ?;";
            Object[] params = {alertAgree, userId};
            jdbcTemplate.update(sql, params);
            return alertAgree;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Post Alert Error");
        }
    }
}
