package com.zatch.zatchserver.repository;

import com.zatch.zatchserver.dto.GetMemberResponseDto;

import java.util.List;

public interface MemberRepository {

    GetMemberResponseDto getMemberById(Long memberId);
    List<GetMemberResponseDto> getAllMembers();
}
