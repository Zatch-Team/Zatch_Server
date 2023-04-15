package com.zatch.zatchserver.repository;


import com.zatch.zatchserver.domain.ExchangeSearch;
import com.zatch.zatchserver.domain.ViewMyZatch;
import com.zatch.zatchserver.domain.Zatch;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
@AllArgsConstructor
public class ZatchRepositoryImpl implements ZatchRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Zatch> findAllByOrderByCreatedAtDesc() {
        return null;
    }

    @Override
    public Long register(Zatch zatch) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into zatch (user_id,category_id,is_Free,item_name, content, quantity, purchase_date, expiration_date, is_opened, allow_any_zatch) values (?,?,?,?,?,?,?,?,?,?)",
                    new String[]{"zatchId"});
            preparedStatement.setLong(1, zatch.getUserId());
            preparedStatement.setLong(2, zatch.getCategoryId());
            preparedStatement.setBoolean(3, zatch.getIsFree());
            preparedStatement.setString(4, zatch.getItemName());
            preparedStatement.setString(5, zatch.getContent());
            preparedStatement.setInt(6, zatch.getQuantity());
            preparedStatement.setDate(7, (Date) zatch.getPurchaseDate());
            preparedStatement.setDate(8, (Date) zatch.getExpirationDate());
            preparedStatement.setInt(9, zatch.getIsOpened());
            preparedStatement.setInt(10, zatch.getAnyZatch());

            return preparedStatement;
        };
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public List<ViewMyZatch> getZatchName(Long userId) {
        List<ViewMyZatch> results = jdbcTemplate.query(
                "SELECT item_name from zatch where user_id = ?",
                new RowMapper<ViewMyZatch>() {
                    @Override
                    public ViewMyZatch mapRow(ResultSet rs, int rowNum) throws SQLException {
                        ViewMyZatch myZatch = new ViewMyZatch(
                                rs.getString("item_name"));
                        return myZatch;
                    }
                }, userId);
        return results.isEmpty() ? null : results;
    }

    @Override
    public List<ExchangeSearch> viewAll(String itemName1, String itemName2) {
        List<ExchangeSearch> results = jdbcTemplate.query(
                "SELECT * from zatch where (item_name LIKE ? OR item_name LIKE ?)",
                new RowMapper<ExchangeSearch>() {
                    @Override
                    public ExchangeSearch mapRow(ResultSet rs, int rowNum) throws SQLException {
                        ExchangeSearch exchangeSearch = new ExchangeSearch(
                                rs.getLong("user_id"),
                                rs.getInt("is_free"),
                                rs.getString("item_name"),
                                rs.getInt("allow_any_zatch"),
                                rs.getInt("like_count")
                        );
                        return exchangeSearch;
                    }
                }, itemName1, itemName2);
        return results.isEmpty() ? null : results;
    }
}


