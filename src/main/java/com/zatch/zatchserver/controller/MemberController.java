package com.zatch.zatchserver.controller;

import com.zatch.zatchserver.dto.GetMemberResponseDto;
import com.zatch.zatchserver.repository.MemberRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping("/{memberId}")
    @ApiOperation(value = "멤버 조회", notes = "멤버 Id로 멤버 조회 API")
    public GetMemberResponseDto getMemberById(@PathVariable("memberId") Long memberId) {
        return memberRepository.getMemberById(memberId);
    }

    @GetMapping("")
    @ApiOperation(value = "전체 멤버 조회", notes = "전체 멤버 조회 API")
    public List<GetMemberResponseDto> getAllMembers() {
        return memberRepository.getAllMembers();
    }
}
