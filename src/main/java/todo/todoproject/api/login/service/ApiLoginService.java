package todo.todoproject.api.login.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import todo.todoproject.api.login.dto.MemberLoginDto;
import todo.todoproject.domain.member.entity.Member;
import todo.todoproject.domain.member.service.MemberService;
import todo.todoproject.global.jwt.JwtLoginResponseDto;
import todo.todoproject.global.jwt.JwtService;

@Service
@RequiredArgsConstructor
public class ApiLoginService {

    private final MemberService memberService;
    private final JwtService jwtService;

    public JwtLoginResponseDto loginMember(MemberLoginDto memberLoginDto) {
        String memberNameFromRequest = memberLoginDto.getMemberName();
        String passwordFromRequest = memberLoginDto.getPassword();

        Member findMember = memberService.findMemberByMemberName(memberNameFromRequest)
                .orElseThrow(() -> new IllegalArgumentException("아이디나 비밀번호가 일치하지 않습니다."));

        validatePassword(passwordFromRequest, findMember.getPassword());

        String accessToken = jwtService.createAccessToken(memberNameFromRequest);
        String refreshToken = jwtService.createRefreshToken(memberNameFromRequest);

        findMember.saveRefreshToken(refreshToken);

        return JwtLoginResponseDto.of(accessToken, refreshToken);
    }

    private void validatePassword(String passwordFromRequest, String passwordFromDB) {
        if (!passwordFromDB.equals(passwordFromRequest)) {
            throw new IllegalArgumentException("아이디나 비밀번호가 일치하지 않습니다.");
        }
    }
}
