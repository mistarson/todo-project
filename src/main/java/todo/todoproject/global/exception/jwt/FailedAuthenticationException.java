package todo.todoproject.global.exception.jwt;

import todo.todoproject.global.exception.common.BusinessException;
import todo.todoproject.global.exception.common.ErrorCode;

public class FailedAuthenticationException extends BusinessException {

    public FailedAuthenticationException() {
        super(ErrorCode.FAILED_AUTHENTICATION_EXCEPTION);
    }

    public FailedAuthenticationException(Throwable cause) {
        super(ErrorCode.FAILED_AUTHENTICATION_EXCEPTION, cause);
    }
}
