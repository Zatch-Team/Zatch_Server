package com.zatch.zatchserver.repository;

import com.zatch.zatchserver.domain.User;
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
    public User selectOneById(Long memberId) {
        return null;
    }

    @Override
    public List<User> selectAll() {
        return null;
    }

    @Override
    public Long insert(User user) {
        return null;
    }
}
