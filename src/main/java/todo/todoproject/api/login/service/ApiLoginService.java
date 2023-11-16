package todo.todoproject.api.login.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import todo.todoproject.api.login.dto.MemberLoginDto;
import todo.todoproject.domain.member.entity.Member;
import todo.todoproject.domain.member.service.MemberService;

@Service
@RequiredArgsConstructor
public class ApiLoginService {

    private final MemberService memberService;

    public void loginMember(MemberLoginDto memberLoginDto) {
        String memberNameFromRequest = memberLoginDto.getMemberName();
        String passwordFromRequest = memberLoginDto.getPassword();

        Member findMember = memberService.findMemberByMemberName(memberNameFromRequest)
                .orElseThrow(() -> new IllegalArgumentException("아이디나 비밀번호가 일치하지 않습니다."));

        if (!isSamePassword(passwordFromRequest, findMember.getPassword())) {
            throw new IllegalArgumentException("아이디나 비밀번호가 일치하지 않습니다.");
        }

        // TODO jwt 발급 로직

    }

    private boolean isSamePassword(String passwordFromRequest, String passwordFromDB) {
        if (passwordFromDB.equals(passwordFromRequest)) {
            return true;
        }
        return false;
    }
}
