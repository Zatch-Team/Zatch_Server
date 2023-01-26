package com.zatch.zatchserver.repository;

import com.zatch.zatchserver.domain.User;
import com.zatch.zatchserver.dto.UserInfo;
import com.zatch.zatchserver.dto.controller.GetUserRes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcUserRepository implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public UserInfo selectOneById(Long userId) {
        String selectOneByIdQuery = "select name, nickname, email from user where user_id = ?";

        return jdbcTemplate.queryForObject(selectOneByIdQuery,
                (rs, rowNum) -> new UserInfo(
                        rs.getString("name"),
                        rs.getString("nickname"),
                        rs.getString("email")),
                userId);
    }

    @Override
    public List<User> selectAll() {
        return null;
    }

    @Override
    public Long insert(User user) {
        String userInsertQuery = "insert into user(name, nickname, email, password) values(?,?,?,?)";
        Object[] userInsertQueryParams = new Object[]{user.getName(), user.getNickname(), user.getEmail(), user.getPassword()};
        jdbcTemplate.update(userInsertQuery, userInsertQueryParams);

        return getLastInsertId();
    }

    @Override
    public Long authenticateBy(String email, String password) {
        String authenticateByQuery = "select user_id from user where email = ? and password = ?";

        return jdbcTemplate.queryForObject(authenticateByQuery, Long.class, new Object[] {email, password});
    }

    public Long getLastInsertId() {
        String getLastInsertedIdQuery = "select last_insert_id()";

        return jdbcTemplate.queryForObject(getLastInsertedIdQuery, Long.class);
    }
}
