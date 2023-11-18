package todo.todoproject.global.exception.jwt;

import todo.todoproject.global.exception.common.BusinessException;
import todo.todoproject.global.exception.common.ErrorCode;

public class ExpiredJwtTokenException extends BusinessException {

    public ExpiredJwtTokenException() {
        super(ErrorCode.EXPIRED_JWT_TOKEN_EXCEPTION);
    }

    public ExpiredJwtTokenException(Throwable cause) {
        super(ErrorCode.EXPIRED_JWT_TOKEN_EXCEPTION, cause);
    }
}
