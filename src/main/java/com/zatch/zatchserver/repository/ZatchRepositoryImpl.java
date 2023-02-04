package com.zatch.zatchserver.repository;


import com.zatch.zatchserver.domain.Zatch;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ZatchRepositoryImpl implements ZatchRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Zatch> findAllByOrderByCreatedAtDesc() {
        return jdbcTemplate.query(
                "insert into 'zatch' "" ,
        (rs, rowNum) ->
                new Zatch(
                        rs.getInt("postId"),
                        rs.getString("id"),
                        rs.getString("title"),
                        rs.getString("category"),
                        rs.getInt("price"),
                        rs.getString("content"),
                        rs.getString("status"),
                        rs.getTimestamp("createDate"),
                        rs.getTimestamp("updateDate"),
                        rs.getString("filePath"),
                        rs.getString("fileDownloadPath")
                )
        );
    }

    @Override
    public Long register() {
        try {
            Object userId = null;
            return jdbcTemplate.query(
                    "SELECT * FROM `post_zatch` WHERE `user_id` = ?",
                    new Object[]{userId},
                    (rs, rowNum) ->
                            new Zatch(new Zatch(
                                    rs.getLong("post_zatch_id"),
                                    rs.getLong("category_id"),
                                    rs.getBoolean("is_free"),
                                    rs.getString("item_name"),
                                    rs.getString("content"),
                                    rs.getInt("quantity"),
                                    rs.getDate("date_buy"),
                                    rs.getDate("date_expired"),
                                    rs.getInt("is_opened"),
                                    rs.getInt("any_zatch")
                            ))
            );
        } catch(EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
