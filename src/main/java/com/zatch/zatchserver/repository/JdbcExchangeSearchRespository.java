package com.zatch.zatchserver.repository;

import com.zatch.zatchserver.domain.ExchangeSearch;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcExchangeSearchRespository implements ExchangeSearchRepository {
    private static Map<Long, ExchangeSearch> search = new HashMap<>();
    private final JdbcTemplate jdbcTemplate;
    public JdbcExchangeSearchRespository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<ExchangeSearch> viewAll(String itemName1, String itemName2) {
        String sql = "SELECT * from user where (email LIKE '?' OR email LIKE '?')";
        Object[] params = {itemName1, itemName2};
        jdbcTemplate.update(sql, params);

        return (List<ExchangeSearch>) jdbcTemplate.queryForObject(sql, new Object[]{itemName1, itemName2}, (rs, rowNum) ->
                new ExchangeSearch(
                        rs.getLong("user_id"),
                        rs.getInt("is_free"),
                        rs.getString("item_name"),
                        rs.getInt("any_zatch"),
                        rs.getInt("like_count")
                ));
    }
}
