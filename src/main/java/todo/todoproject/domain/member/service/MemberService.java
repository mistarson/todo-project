package todo.todoproject.domain.member.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todo.todoproject.domain.member.entity.Member;
import todo.todoproject.domain.member.repository.MemberRepository;
import todo.todoproject.global.exception.member.MemberNotFoundException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Optional<Member> findMemberByMemberName(String memberName) {
        return memberRepository.findMemberByMemberName(memberName);
    }

    public void registerMember(Member member) {
        memberRepository.save(member);
    }

    @Transactional
    public void updateRefreshToken(String refreshToken, String memberName) {
        Member member = memberRepository.findMemberByMemberName(memberName)
                .orElseThrow(MemberNotFoundException::new);
        member.saveRefreshToken(refreshToken);
    }
}
