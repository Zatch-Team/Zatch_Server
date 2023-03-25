package com.zatch.zatchserver.repository;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public class ChatRepository implements ChatRepositoryImpl {
    private final JdbcTemplate jdbcTemplate;

    public ChatRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String after_deal(Long send_user_id, Long receive_user_id, String review_context, int star_rating) {
        try {
            String sql = "INSERT INTO review_star(send_user_id, receive_user_id, review_context, star_rating) VALUES(?, ?, ?, ?)";
            Object[] params = {send_user_id, receive_user_id, review_context, star_rating};
            jdbcTemplate.update(sql, params);
            System.out.println("after_deal sql insert");
            return send_user_id + " -> " + receive_user_id;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "After Chat >> Review or Star Not Found");
        }
    }
}
