package com.zatch.zatchserver.repository.jpa;

import com.zatch.zatchserver.domain.jpa.Member;

import java.util.List;

public interface MemberRepository {

    Member selectOneById(Long memberId);
    List<Member> selectAll();

    void insertOne(Member newMember);

    Long updateNickname(Long memberId, String newNickname);
}
