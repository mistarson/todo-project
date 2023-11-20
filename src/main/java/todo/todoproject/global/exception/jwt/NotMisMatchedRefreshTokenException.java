package todo.todoproject.global.exception.jwt;

import todo.todoproject.global.exception.common.BusinessException;
import todo.todoproject.global.exception.common.ErrorCode;

public class NotMisMatchedRefreshTokenException extends BusinessException {
    public NotMisMatchedRefreshTokenException() {
        super(ErrorCode.NOT_MISMATCHED_REFRESH_TOKEN_EXCEPTION);
    }
}
