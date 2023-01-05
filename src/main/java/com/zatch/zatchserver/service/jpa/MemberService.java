package com.zatch.zatchserver.service.jpa;

import com.zatch.zatchserver.domain.jpa.Member;

import java.util.List;

public interface MemberService {
    Member save(Member member);

    Member findOne(Long memberId);

    List<Member> findAll();

    Long modifyNickname(Long memberId, String newNickname);
}
