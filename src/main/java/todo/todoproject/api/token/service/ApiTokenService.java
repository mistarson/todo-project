package todo.todoproject.api.token.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import todo.todoproject.domain.member.entity.Member;
import todo.todoproject.domain.member.service.MemberService;
import todo.todoproject.global.exception.jwt.NotMisMatchedRefreshTokenException;
import todo.todoproject.global.exception.jwt.NotRefreshTokenException;
import todo.todoproject.global.exception.member.MemberNotFoundException;
import todo.todoproject.global.jwt.JwtManager;
import todo.todoproject.global.jwt.TokenType;

@Service
@RequiredArgsConstructor
public class ApiTokenService {

    private final JwtManager jwtManager;

    private final MemberService memberService;

    public String reissueAccessToken(String refreshToken) {

        String memberName = jwtManager.getMemberNameFromToken(refreshToken);

        validateRefreshToken(refreshToken, memberName);

        return jwtManager.createAccessToken(memberName);
    }

    private void validateRefreshToken(String refreshToken, String memberName) {

        if (!isRefreshToken(refreshToken)) {
            throw new NotRefreshTokenException();
        }

        if (!isRightRefreshToken(refreshToken, memberName)) {
            throw new NotMisMatchedRefreshTokenException();
        }
    }

    private boolean isRefreshToken(String refreshToken) {

        String tokenType = jwtManager.getTokenTypeFromToken(refreshToken);

        return TokenType.REFRESH.toString().equals(tokenType);
    }

    private boolean isRightRefreshToken(String refreshToken, String memberName) {

        Member findMember = memberService.findMemberByMemberName(memberName)
                .orElseThrow(MemberNotFoundException::new);

        return findMember.getRefreshToken().equals(refreshToken);
    }
}
