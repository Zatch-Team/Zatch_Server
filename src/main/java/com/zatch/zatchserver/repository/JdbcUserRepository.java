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
    public Long insert(User user) {
        String sql = "INSERT INTO user(name, nickname, email, password) VALUES(?, ?, ?, ?)";
        Object[] params = {user.getName(), user.getNickname(), user.getEmail(), user.getPassword()};
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
}
