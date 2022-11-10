package com.zatch.zatchserver.repository;

import com.zatch.zatchserver.domain.Member;
import com.zatch.zatchserver.dto.GetMemberResDto;

import java.util.List;

public interface MemberRepository {

    Member selectOne(Long memberId);
    List<Member> selectAll();

    void insertOne(Member newMember);

    Long updateNickname(Long memberId, String newNickname);
}
