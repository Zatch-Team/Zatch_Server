package com.zatch.zatchserver.repository;

import com.zatch.zatchserver.domain.Search;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcSearchRepository implements SearchRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcSearchRepository(DataSource dataSource){
        this.jdbcTemplate= new JdbcTemplate(dataSource);
    }

    @Override
    public List<Search> findByItemName(String keyword) {

        String sql="SELECT is_free, item_name, content, any_zatch FROM post_zatch WHERE item_name LIKE '%?%'";
//         return null;
        return (List<Search>) jdbcTemplate.queryForObject(sql, new Object[]{keyword}, (rs, rowNum) ->
                new Search(
                        rs.getInt("is_free"),
                        rs.getString("item_name"),
                        rs.getString("content"),
                        rs.getInt("any_zatch")
                ));
    }
}
