package com.zatch.zatchserver.repository;

import com.zatch.zatchserver.domain.Zatch;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

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

}
