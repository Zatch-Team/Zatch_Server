package com.zatch.zatchserver.repository;

import com.zatch.zatchserver.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcUserRepository implements UserRepository{

    private static Map<Long, User> user = new HashMap<>();
    private final JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User selectOneById(Long memberId) {
        return null;
    }

    @Override
    public List<User> selectAll() {
        return null;
    }

    @Override
    public String isSignup(String email) {
        try{
            String sql = "SELECT user_id from user WHERE email = ?";
            Object[] params = {email};
            System.out.println("Is Login or Signup SQL select");
            // empty >> signup, not_empty >> login
            if (jdbcTemplate.queryForList(sql, params).isEmpty()){
                return "signup";
            }
            return "login";
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User Email Not Found");
        }
    }

    @Override
    public String getUserId(String email) {
        try{
            String sql = "SELECT user_id from user WHERE email = ?";
            Object[] params = {email};
            System.out.println("Login SQL select");
            String user_id = String.valueOf(jdbcTemplate.queryForList(sql, params).get(0).get("user_id"));
            return user_id;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User Email Not Found");
        }
    }

    @Override
    public Long insert(User user) {
        try {
            System.out.println("user >>> : "+ user.getName());
            String sql = "INSERT INTO user(name, nickname, email) VALUES(?, ?, ?)";
            Object[] params = {user.getName(), user.getNickname(), user.getEmail()};
            jdbcTemplate.update(sql, params);
            System.out.println("Signup sql insert");
            return user.getId();
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User Email Not Found");
        }
    }

    @Override
    public Long modifyNickname(Long userId, String newNickname) {
        try {
            String sql = "UPDATE user SET nickname = ? WHERE user_id = ?";
            Object[] params = {newNickname, userId};
            jdbcTemplate.update(sql, params);
            System.out.println("Modify nickname sql update");
            return userId;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User Email Not Found");
        }
    }

    @Override
    public List<Map<String, Object>> profile(Long userId) {
        try {
            String sql = "SELECT user.user_id, user.nickname, zatch.zatch_id, zatch.item_name review_context, star_rating " +
                    "FROM zatch.review_star LEFT JOIN zatch.zatch on review_star.send_user_id = zatch.user_id LEFT JOIN zatch.user on zatch.user_id = user.user_id " +
                    "WHERE user.user_id = ? ORDER BY review_star.created_at DESC;";
            Object[] params = {userId};
            System.out.println("User's profile SQL select");
            return jdbcTemplate.queryForList(sql, params);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User Profile Not Found");
        }
    }

    @Override
    public String townInsert(Long userId, String town){
        try {
            String town1 = jdbcTemplate.queryForObject("SELECT town1 from user WHERE user_id = ?", new Object[]{userId}, String.class);
            String town2 = jdbcTemplate.queryForObject("SELECT town2 from user WHERE user_id = ?", new Object[]{userId}, String.class);
            String town3 = jdbcTemplate.queryForObject("SELECT town3 from user WHERE user_id = ?", new Object[]{userId}, String.class);
            if (town1 == null){
                jdbcTemplate.update("UPDATE user SET town1 = ? WHERE user_id = ?", town, userId);
                System.out.println("User's town1 sql update");
            }
            else if (town2 == null){
                jdbcTemplate.update("UPDATE user SET town2 = ? WHERE user_id = ?", town, userId);
                System.out.println("User's town2 sql update");
            }
            else if (town3 == null){
                jdbcTemplate.update("UPDATE user SET town3 = ? WHERE user_id = ?", town, userId);
                System.out.println("User's town3 sql update");
            }
            else {
                System.out.println("User's town over 3");
            }
            return town;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User Email Not Found");
        }
    }

    @Override
    public String insertToken(Long userId, String token) {
        try {
            jdbcTemplate.update("UPDATE zatch.user SET token = ? WHERE user_id = ?", token, userId);
            System.out.println("Token Insert sql insert");
            return token;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Token UPDATE Error");
        }
    }

    @Override
    public List<Map<String, Object>> getMypage(Long userId) {
        try {
            String sql = "SELECT user.user_id, user.nickname, COUNT(*) AS zatch_count, (SELECT COUNT(zatch_like.zatch_id) AS zatch_like_count FROM zatch.zatch_like WHERE user_id = 6) AS zatch_like_count " +
                    "FROM zatch.zatch AS A LEFT JOIN zatch.user on user.user_id = A.user_id WHERE user.user_id = ?;";
            Object[] params = {userId};
            System.out.println("User's My Page SQL select");
            return jdbcTemplate.queryForList(sql, params);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User My Page Not Found");
        }
    }

}
