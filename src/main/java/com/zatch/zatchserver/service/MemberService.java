package com.zatch.zatchserver.service;

import com.zatch.zatchserver.domain.Member;
import com.zatch.zatchserver.dto.GetMemberResDto;

import java.util.List;

public interface MemberService {
    Member save(Member member);

    Member findOne(Long memberId);

    List<Member> findAll();

    Long modifyNickname(Long memberId, String newNickname);
}
