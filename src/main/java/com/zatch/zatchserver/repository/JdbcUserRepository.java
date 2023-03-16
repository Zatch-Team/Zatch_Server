package com.zatch.zatchserver.repository;

import com.zatch.zatchserver.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
    public Boolean isSignup(String email) {
        String sql = "SELECT user_id from user WHERE email = ?";
        Object[] params = {email};
        System.out.println("Is Login or Signup SQL select");
        // empty >> signup, not_empty >> login
        if (jdbcTemplate.queryForList(sql, params).isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public String getUserId(String email) {
        String sql = "SELECT user_id from user WHERE email = ?";
        Object[] params = {email};
        System.out.println("Login SQL select");
        String user_id = String.valueOf(jdbcTemplate.queryForList(sql, params).get(0).get("user_id"));
        return user_id;
    }

    @Override
    public Long insert(User user) {
        String sql = "INSERT INTO user(name, nickname, email) VALUES(?, ?, ?)";
        Object[] params = {user.getName(), user.getNickname(), user.getEmail()};
        jdbcTemplate.update(sql, params);
        System.out.println("Signup sql insert");
        return user.getId();
    }

    @Override
    public Long modifyNickname(Long userId, String newNickname) {
        String sql = "UPDATE user SET nickname = ? WHERE user_id = ?";
        Object[] params = {newNickname, userId};
        jdbcTemplate.update(sql, params);
        System.out.println("Modify nickname sql update");
        return userId;
    }

    @Override
    public List<Map<String, Object>> profile(Long userId) {
        String sql = "SELECT nickname from user WHERE user_id = ?";
        Object[] params = {userId};
        System.out.println("User's profile SQL select");
        return jdbcTemplate.queryForList(sql, params);
    }

    @Override
    public String townInsert(Long userId, String town){
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
    }
}
