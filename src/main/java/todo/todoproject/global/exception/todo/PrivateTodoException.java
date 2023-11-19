package todo.todoproject.global.exception.todo;

import todo.todoproject.global.exception.common.BusinessException;
import todo.todoproject.global.exception.common.ErrorCode;

public class PrivateTodoException extends BusinessException {
    public PrivateTodoException() {
        super(ErrorCode.TODO_PRIVATE_EXCEPTION);
    }
}
