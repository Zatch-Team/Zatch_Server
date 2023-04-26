package com.zatch.zatchserver.repository;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public class BlockRepositoryImpl implements BlockRepository {
    private final JdbcTemplate jdbcTemplate;

    public BlockRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String postBlock(Long userId, Long blockedId) {
        try {
            String sql = "INSERT INTO block (blocked_id, blocker_id) VALUES (?, ?);";
            Object[] params = {userId, blockedId};
            jdbcTemplate.update(sql, params);
            return userId + " -> " + blockedId + "Block Success";
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Post Block Error");
        }
    }
}
