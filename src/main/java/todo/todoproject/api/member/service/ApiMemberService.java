package todo.todoproject.api.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todo.todoproject.api.member.dto.MemberRegisterDto;
import todo.todoproject.domain.member.service.MemberService;
import todo.todoproject.global.exception.member.MemberAlreadyExistException;

@Service
@RequiredArgsConstructor
public class ApiMemberService {

    private final MemberService memberService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void registerMember(MemberRegisterDto memberRegisterDto) {
        String memberName = memberRegisterDto.getMemberName();
        memberService.findMemberByMemberName(memberName)
                .ifPresent(member -> {
                    throw new MemberAlreadyExistException();
                });

        memberService.registerMember(memberRegisterDto.toEntity(bCryptPasswordEncoder));
    }
}
