package todo.todoproject.api.todo.dto;

import java.time.LocalDateTime;
import todo.todoproject.domain.todo.entity.Todo;

public record TodosInquireDto(
        String todoTitle,
        String todoContent,
        LocalDateTime createdDate,
        boolean isCompleted
) {

    public static TodosInquireDto from(Todo todo) {
        return new TodosInquireDto(todo.getTodoTitle(), todo.getTodoContent(), todo.getCreatedTime(),
                todo.isCompleted());
    }

}
