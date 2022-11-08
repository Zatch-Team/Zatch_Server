package com.zatch.zatchserver.repository;

import com.zatch.zatchserver.dto.GetMemberResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class H2MemberRepository implements MemberRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public H2MemberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public GetMemberResponseDto getMemberById(Long memberId) {
        String getMemberByIdQuery = "select * from member where member_id = ?";

        return this.jdbcTemplate.queryForObject(getMemberByIdQuery,
                (rs, rowNum) -> new GetMemberResponseDto(
                        rs.getLong("member_id"),
                        rs.getString("member_name")),
                memberId);
    }

    @Override
    public List<GetMemberResponseDto> getAllMembers() {
        String getAllMembersQuery = "select * from member";

        return this.jdbcTemplate.query(getAllMembersQuery,
                (rs, rowNum) -> new GetMemberResponseDto(
                        rs.getLong("member_id"),
                        rs.getString("member_name")));
    }
}
