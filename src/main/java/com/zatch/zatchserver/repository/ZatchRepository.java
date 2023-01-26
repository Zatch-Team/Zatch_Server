package com.zatch.zatchserver.repository;

import com.zatch.zatchserver.domain.Zatch;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;

@Repository
public class ZatchRepository {
    private JdbcTemplate jdbcTemplate;

    public ZatchRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void save(Zatch newZatch) {
        String saveZatchQuery = "insert into zatch(" +
                "user_id, " +
                "category_id, " +
                "is_free, " +
                "item_name, " +
                "content, " +
                "quantity, " +
                "purchase_date, " +
                "expiration_date, " +
                "is_opened, " +
                "allow_any_zatch) " +
                "values(?,?,?,?,?,?,?,?,?,?)";
        Object[] saveZatchParams = new Object[]{
                newZatch.getUserId(),
                newZatch.getCategoryId(),
                newZatch.getIsFree(),
                newZatch.getItemName(),
                newZatch.getContent(),
                newZatch.getQuantity(),
                newZatch.getPurchaseDate(),
                newZatch.getExpirationDate(),
                newZatch.getIsOpened(),
                newZatch.getAllowAnyZatch()
        };

        jdbcTemplate.update(saveZatchQuery, saveZatchParams);
    }

    public Integer increaseLike(Long userId, Long zatchId) {
        String increaseLikeQuery = "insert into zatch_like(user_id, zatch_id) values(?,?)";

        jdbcTemplate.update(increaseLikeQuery, new Object[] {userId, zatchId});

        String getZatchLikeCountQuery = "select count(zatch_like_id) from zatch_like where zatch_id = ?";
        return jdbcTemplate.queryForObject(getZatchLikeCountQuery, Integer.class, zatchId);
    }
}
