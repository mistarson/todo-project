package todo.todoproject.api.token.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import todo.todoproject.api.token.service.ApiTokenService;
import todo.todoproject.global.util.JwtUtil;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/token")
public class ApiTokenController {

    private final ApiTokenService apiTokenService;

    @GetMapping("/reissue")
    public ResponseEntity<?> reissueAccessToken(HttpServletRequest req) {

        String refreshToken = JwtUtil.getTokenFromRequest(req);

        String accessToken = apiTokenService.reissueAccessToken(refreshToken);

        return ResponseEntity.ok(accessToken);
    }
}
