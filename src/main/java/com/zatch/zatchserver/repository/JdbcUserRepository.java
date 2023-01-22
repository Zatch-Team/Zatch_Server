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
        return user.get(memberId);
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
        System.out.println("회원가입 sql insert");
        return user.getId();
    }

    @Override
    public Long modifyNickname(Long userId) {
        User modUserId = selectOneById(userId);
        return modUserId.getId();
    }
}
