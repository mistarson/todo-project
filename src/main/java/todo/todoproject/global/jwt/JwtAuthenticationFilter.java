package todo.todoproject.global.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import todo.todoproject.domain.member.service.MemberService;
import todo.todoproject.global.security.UserDetailsImpl;

@Slf4j(topic = "로그인 및 JWT 생성")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtManager jwtManager;

    private final MemberService memberService;

    public JwtAuthenticationFilter(JwtManager jwtManager, MemberService memberService) {
        this.jwtManager = jwtManager;
        this.memberService = memberService;
        setFilterProcessesUrl("/api/members/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        log.info("로그인 시도");
        try {
            MemberLoginDto memberLoginDto = new ObjectMapper().readValue(request.getInputStream(),
                    MemberLoginDto.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            memberLoginDto.getMemberName(),
                            memberLoginDto.getPassword(),
                            null
                    )
            );
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) {
        log.info("로그인 성공 및 JWT 생성");
        String memberName = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();

        String accessToken = jwtManager.createAccessToken(memberName);
        String refreshToken = jwtManager.createRefreshToken(memberName);

        saveRefreshToken(refreshToken, memberName);

        response.setHeader("Access-Token", accessToken);
        response.setHeader("Refresh-Token", refreshToken);
    }

    private void saveRefreshToken(String refreshToken, String memberName) {
        memberService.updateRefreshToken(refreshToken, memberName);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        log.info("로그인 실패");
        response.setStatus(401);
    }
}
