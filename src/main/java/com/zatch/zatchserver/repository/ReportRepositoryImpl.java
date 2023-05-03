package com.zatch.zatchserver.repository;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public class ReportRepositoryImpl implements ReportRepository {
    private final JdbcTemplate jdbcTemplate;

    public ReportRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String postReport(Long userId, Long reportedId, int reportReason) {
        try {
            String sql = "INSERT INTO report (reported_id, reporter_id, report_reason) VALUES (?, ?, ?);";
            Object[] params = {userId, reportedId, reportReason};
            jdbcTemplate.update(sql, params);
            return userId + " -> " + reportedId + "Report Success";
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Post Report Error");
        }
    }

}
