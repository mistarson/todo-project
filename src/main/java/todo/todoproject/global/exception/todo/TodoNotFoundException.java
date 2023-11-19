package todo.todoproject.global.exception.todo;

import todo.todoproject.global.exception.common.BusinessException;
import todo.todoproject.global.exception.common.ErrorCode;

public class TodoNotFoundException extends BusinessException {
    public TodoNotFoundException() {
        super(ErrorCode.NOT_FOUND_TODO_EXCEPTION);
    }
}
