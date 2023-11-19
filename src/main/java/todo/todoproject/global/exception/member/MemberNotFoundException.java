package todo.todoproject.global.exception.member;

import todo.todoproject.global.exception.common.BusinessException;
import todo.todoproject.global.exception.common.ErrorCode;

public class MemberNotFoundException extends BusinessException {

    public MemberNotFoundException() {
        super(ErrorCode.NOT_FOUND_MEMBER_EXCEPTION);
    }

    public MemberNotFoundException(Throwable cause) {
        super(ErrorCode.NOT_FOUND_MEMBER_EXCEPTION, cause);
    }
}
