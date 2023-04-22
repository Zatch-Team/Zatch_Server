package com.zatch.zatchserver.repository;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Repository
public class ChatRepository implements ChatRepositoryImpl {
    private final JdbcTemplate jdbcTemplate;

    public ChatRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Map<String, Object>> selectAllChatRoom(String userId) {
        try {
            String sql = "SELECT chat_id, chat_room_id, chat_sender, chat_receiver, chat_message, profile_img_url, chat.updated_at " +
                    "FROM chat LEFT JOIN user on user.user_id = chat.chat_receiver " +
                    "WHERE chat_sender = ? or chat_receiver = ? " +
                    "GROUP BY chat_room_id " +
                    "ORDER BY chat.updated_at DESC;";
            Object[] params = {userId, userId};
            System.out.println("User's Chat List SQL select");
            return jdbcTemplate.queryForList(sql, params);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User's Chat List Not Found");
        }
    }

    @Override
    public String updateDB(String type, String roomId, String sender, String receiver, String message) {
        try {
            String chat_info = "type : " + type + " / roomId : " + roomId + " / sender" + sender + " / receiver : " + receiver + " / message : " + message;
            System.out.println(chat_info);
            String sql = "INSERT INTO chat(chat_type, chat_room_id, chat_sender, chat_receiver, chat_message) VALUES(?, ?, ?, ?, ?)";
            Object[] params = {type, roomId, sender, receiver, message};
            jdbcTemplate.update(sql, params);
            System.out.println("chat sql insert");
            return chat_info;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Chat SQL ERROR");
        }
    }

    @Override
    public String sendImage(String type, String roomId, String sender, String receiver, String imgUrl) {
        try {
            String chat_info = "type : " + type + " / roomId : " + roomId + " / sender" + sender + " / receiver : " + receiver + " / imgUrl : " + imgUrl;
            System.out.println(chat_info);
            String sql = "INSERT INTO chat(chat_type, chat_room_id, chat_sender, chat_receiver, chat_img_url) VALUES(?, ?, ?, ?, ?)";
            Object[] params = {type, roomId, sender, receiver, imgUrl};
            jdbcTemplate.update(sql, params);
            System.out.println("chat img sql insert");
            return chat_info;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Chat SQL ERROR");
        }
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
