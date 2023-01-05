package com.zatch.zatchserver.service.jpa;

import com.zatch.zatchserver.domain.jpa.Member;
import com.zatch.zatchserver.repository.jpa.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public Member findOne(Long memberId) {
        return memberRepository.selectOneById(memberId);
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.selectAll();
    }

    @Override
    public Member save(Member newMember) {
        memberRepository.insertOne(newMember);
        return newMember;
    }

    @Override
    public Long modifyNickname(Long memberId, String newNickname) {
        return memberRepository.updateNickname(memberId, newNickname);
    }

}
