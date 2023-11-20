package todo.todoproject.global.exception.jwt;

import todo.todoproject.global.exception.common.BusinessException;
import todo.todoproject.global.exception.common.ErrorCode;

public class NotRefreshTokenException extends BusinessException {
    public NotRefreshTokenException() {
        super(ErrorCode.NOT_REFRESH_TOKEN_EXCEPTION);
    }
}
