package com.zatch.zatchserver.controller;

import com.zatch.zatchserver.domain.jpa.Member;
import com.zatch.zatchserver.dto.*;
import com.zatch.zatchserver.repository.jpa.MemberRepository;
import com.zatch.zatchserver.service.jpa.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping("/{memberId}")
    @ApiOperation(value = "회원 조회", notes = "회원 id로 회원 조회 API")
    public GetUserResDto getMember(@PathVariable("memberId") Long memberId) {
        Member findMember = memberService.findOne(memberId);

        return new GetUserResDto(findMember.getName(), findMember.getNickname(), findMember.getEmail());
    }

    @GetMapping("")
    @ApiOperation(value = "전체 회원 조회", notes = "전체 회원 조회 API")
    public List<GetUserResDto> getAllMembers() {
        List<Member> findMembers = memberService.findAll();

        List<GetUserResDto> getMemberResDtos = new ArrayList<>();
        for (Member findMember : findMembers) {
            getMemberResDtos.add(new GetUserResDto(findMember.getName(), findMember.getNickname(),
                    findMember.getEmail()));
        }

        return getMemberResDtos;
    }

    @PostMapping("/new")
    @ApiOperation(value = "회원가입", notes = "회원가입 API")
    public PostUserResDto postMember(@RequestBody PostUserReqDto postMemberReqDto) {
        Member newMember = Member.createMember(
                postMemberReqDto.getName(),
                postMemberReqDto.getNickname(),
                postMemberReqDto.getEmail(),
                postMemberReqDto.getPassword()
        );

        memberService.save(newMember);

        return new PostUserResDto(newMember.getName(), newMember.getEmail());
    }

    @PatchMapping("/{memberId}/nickname")
    @ApiOperation(value = "회원 닉네임 수정", notes = "회원 닉네임 수정 API")
    public PatchUserNicknameResDto patchNickname(@PathVariable("memberId") Long memberId,
                                                 @RequestBody PatchUserNicknameReqDto pathMemberNicknameReqDto) {
        String newNickname = pathMemberNicknameReqDto.getNewNickname();
        Long idOfModifiedMember = memberService.modifyNickname(memberId, newNickname);

        Member modifiedMember = memberService.findOne(idOfModifiedMember);
        return new PatchUserNicknameResDto(modifiedMember.getNickname());
    }
}
