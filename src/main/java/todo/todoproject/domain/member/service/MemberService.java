package todo.todoproject.domain.member.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import todo.todoproject.domain.member.entity.Member;
import todo.todoproject.domain.member.repository.MemberRepository;

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
}
