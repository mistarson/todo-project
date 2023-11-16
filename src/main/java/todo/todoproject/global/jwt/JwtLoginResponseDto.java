package todo.todoproject.global.jwt;

import lombok.Getter;

@Getter
public class JwtLoginResponseDto {

    private final String accessToken;

    private final String refreshToken;

    private JwtLoginResponseDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static JwtLoginResponseDto of(String accessToken, String refreshToken) {
        return new JwtLoginResponseDto(accessToken, refreshToken);
    }
}
