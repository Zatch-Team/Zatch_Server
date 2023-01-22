package com.zatch.zatchserver.repository;


import com.zatch.zatchserver.domain.Post;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public int save(Post post) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `post_zatch` ('category_id','is_free',`item_name`, `content`, `quantity`, `date_buy`, `date_expire`, `is_opened`, `any_zatch`)" +
                    "VALUES (?,?,?,?,?,?,?,?)", new String[]{"post_match_id"});
            preparedStatement.setLong(1, post.getCategory_id());
            preparedStatement.setInt(2, post.getIs_free());
            preparedStatement.setString(3, post.getItem_name());
            preparedStatement.setString(4, post.getContent());
            preparedStatement.setInt(5, post.getQuantity());
            preparedStatement.setDate(6, (Date) post.getDate_buy());
            preparedStatement.setDate(6, (Date) post.getDate_expire());
            preparedStatement.setInt(7, post.getIs_opened());
            preparedStatement.setInt(8, post.getAny_zatch());

            return preparedStatement;
        };
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public Optional<Post> findById(int postId) {
        try {
            Object userId = null;
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM `post_zatch` WHERE `user_id` = ?",
                    new Object[]{userId},
                    (rs, rowNum) ->
                            Optional.of(new Post(
                                    rs.getLong("post_zatch_id"),
                                    rs.getLong("category_id"),
                                    rs.getInt("is_free"),
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
