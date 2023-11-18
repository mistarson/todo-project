package todo.todoproject.global.exception.jwt;

import todo.todoproject.global.exception.common.BusinessException;
import todo.todoproject.global.exception.common.ErrorCode;

public class InvalidJwtSignatureException extends BusinessException {

    public InvalidJwtSignatureException() {
        super(ErrorCode.INVALID_JWT_SIGNATURE_EXCEPTION);
    }

    public InvalidJwtSignatureException(Throwable cause) {
        super(ErrorCode.INVALID_JWT_SIGNATURE_EXCEPTION, cause);
    }
}
