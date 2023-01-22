package com.zatch.zatchserver.repository;

import com.zatch.zatchserver.domain.Search;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class JdbcSearchRepository implements SearchRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcSearchRepository(DataSource dataSource){
        this.jdbcTemplate= new JdbcTemplate(dataSource);
    }

    @Override
    public List<Search> findByItemName(String keyword) {

        String sql="SELECT post_zatch_id ,is_free, item_name, content, any_zatch FROM post_zatch WHERE item_name LIKE '%?%'";

        return (List<Search>) jdbcTemplate.queryForObject(sql, new Object[]{keyword}, (rs, rowNum) ->
                new Search(
                        rs.getInt("is_free"),
                        rs.getString("item_name"),
                        rs.getString("content"),
                        rs.getInt("any_zatch")
                ));
    }
}
