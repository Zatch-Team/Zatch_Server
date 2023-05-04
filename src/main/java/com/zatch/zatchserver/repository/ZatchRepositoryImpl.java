package com.zatch.zatchserver.repository;


import com.zatch.zatchserver.domain.ExchangeSearch;
import com.zatch.zatchserver.domain.ViewMyZatch;
import com.zatch.zatchserver.domain.Zatch;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


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
        try {
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
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User Id Not Found");
        }
    }

    public Integer increaseLike(Long userId, Long zatchId) {

        String increaseLikeQuery = "insert into zatch_like(user_id, zatch_id) values(?,?)";

        jdbcTemplate.update(increaseLikeQuery, new Object[] {userId, zatchId});

        String getZatchLikeCountQuery = "select count(zatch_like_id) from zatch_like where zatch_id = ?";
        return jdbcTemplate.queryForObject(getZatchLikeCountQuery, Integer.class, zatchId);
    }

    public Integer decreaseLike(Long userId, Long zatchId) {
        String decreaseLikeQuery = "delete from zatch_like where user_id = ? and zatch_id = ?";

        jdbcTemplate.update(decreaseLikeQuery, new Object[] {userId, zatchId});

        String getZatchLikeCountQuery = "select count(zatch_like_id) from zatch_like where zatch_id = ?";
        return jdbcTemplate.queryForObject(getZatchLikeCountQuery, Integer.class, zatchId);
    }

    //지금 인기있는 재치 item 3개 보여주기
    public List<Map<String, Object>> showPopularZatch(){

        String showPopularZatchQuery=
                "SELECT item_name, count(*) as like_count, zatch.created_at FROM zatch_like left join zatch on zatch_like.zatch_id=zatch.zatch_id GROUP BY item_name ORDER BY like_count desc, zatch.created_at desc";

//        List<String> itemName = null;
//        for(int i=0;i<3;i++){
//            //System.out.println("@@@@@@@@@ showPopularZatch");
//            String name=String.valueOf(jdbcTemplate.queryForList(showPopularZatchQuery).get(i).get("item_name"));
//            //System.out.println(name);
//            //itemName.add(String.valueOf(jdbcTemplate.queryForList(showPopularZatchQuery).get(i).get("item_name")));
//            itemName.add(name);
//        }
        //return itemName;
        return jdbcTemplate.queryForList(showPopularZatchQuery);
    }

    @Override
    public List<ExchangeSearch> viewAll(String itemName1, String itemName2) {
        try {
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

        } catch (
                Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Item Name Not Found");
        }
    }
}


