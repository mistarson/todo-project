package todo.todoproject.api.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import todo.todoproject.api.member.dto.MemberRegisterDto;
import todo.todoproject.domain.member.service.MemberService;

@Service
@RequiredArgsConstructor
public class ApiMemberService {

    private final MemberService memberService;

    public void registerMember(MemberRegisterDto memberRegisterDto) {
        String memberName = memberRegisterDto.getMemberName();
        memberService.findMemberByMemberName(memberName)
                .ifPresent(member -> {
                    throw new IllegalArgumentException("이미 존재하는 이름입니다.");
                });

        memberService.registerMember(memberRegisterDto.toEntity());
    }
}
