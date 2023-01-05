package com.zatch.zatchserver.repository.jpa;

import com.zatch.zatchserver.domain.jpa.Member;

import java.util.List;

public class MySqlMemberRepository implements MemberRepository{
    @Override
    public Member selectOneById(Long memberId) {
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
