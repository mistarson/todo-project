package todo.todoproject.api.todo.dto;

import java.time.LocalDateTime;
import todo.todoproject.domain.todo.entity.Todo;

public record TodoInquireDto(
        String todoTile,
        String memberName,
        LocalDateTime createdDate,
        boolean isCompleted
) {

    public static TodoInquireDto from(Todo todo) {
        return new TodoInquireDto(todo.getTodoTitle(), todo.getMember().getMemberName(), todo.getCreatedTime(),
                todo.isCompleted());
    }

}
