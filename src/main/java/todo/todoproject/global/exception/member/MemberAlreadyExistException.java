package todo.todoproject.global.exception.member;

import todo.todoproject.global.exception.common.BusinessException;
import todo.todoproject.global.exception.common.ErrorCode;

public class MemberAlreadyExistException extends BusinessException {
    public MemberAlreadyExistException() {
        super(ErrorCode.ALREADY_EXIST_MEMBER_EXCEPTION);
    }
}
