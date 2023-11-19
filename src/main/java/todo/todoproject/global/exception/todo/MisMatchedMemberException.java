package todo.todoproject.global.exception.todo;

import todo.todoproject.global.exception.common.BusinessException;
import todo.todoproject.global.exception.common.ErrorCode;

public class MisMatchedMemberException extends BusinessException {
    public MisMatchedMemberException() {
        super(ErrorCode.MISMATCHED_MEMBER_EXCEPTION);
    }
}
