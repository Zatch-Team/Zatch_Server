package com.zatch.zatchserver.repository;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class NoticeRepositoryImpl implements NoticeRepository {
    private final JdbcTemplate jdbcTemplate;

    public NoticeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Map<String, Object>> getAllNotice() {
        try {
            String sql = "SELECT notice_title, notice_content, created_at, updated_at FROM notice ORDER BY updated_at DESC;";
            return jdbcTemplate.query(sql, new MapRowMapper());
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Get Notice Error");
        }
    }
    class MapRowMapper implements RowMapper<Map<String, Object>> {
        @Override
        public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            Map<String, Object> map = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                Object columnValue = rs.getObject(i);
                map.put(columnName, columnValue);
            }
            return map;
        }
    }
}
