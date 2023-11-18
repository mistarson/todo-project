package todo.todoproject.global.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

public class JwtUtil {
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";

    public static String getTokenFromRequest(HttpServletRequest req) {
        String jwt = req.getHeader(AUTHORIZATION_HEADER);

        if (StringUtils.hasText(jwt) && jwt.startsWith(BEARER_PREFIX)) {
            return jwt.substring(BEARER_PREFIX.length());
        }

        return null;
    }
}
