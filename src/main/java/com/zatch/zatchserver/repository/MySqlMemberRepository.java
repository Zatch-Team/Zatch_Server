package com.zatch.zatchserver.repository;

import com.zatch.zatchserver.domain.Member;
import com.zatch.zatchserver.dto.GetMemberResDto;

import java.util.List;

public class MySqlMemberRepository implements MemberRepository{
    @Override
    public Member selectOne(Long memberId) {
        return null;
    }

    @Override
    public List<Member> selectAll() {
        return null;
    }

    @Override
    public void insertOne(Member newMember) {

    }

    @Override
    public Long updateNickname(Long memberId, String newNickname) {
        return null;
    }
}
