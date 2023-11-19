package todo.todoproject.api.todo.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import todo.todoproject.domain.todo.entity.Todo;

public class TodoModifyDto {
    public static record Request(@NotBlank String todoTitle,
                                 @NotBlank String todoContent) {
        public Todo toEntity() {
            return Todo.builder()
                    .todoTitle(todoTitle)
                    .todoContent(todoContent)
                    .build();
        }
    }

    public static record Response(String todoTitle,
                           String todoContent,
                           String memberName,
                           LocalDateTime createdDate) {

        public static TodoModifyDto.Response from(Todo todo) {
            return new Response(todo.getTodoTitle(), todo.getTodoContent(), todo.getMember().getMemberName(),
                    todo.getCreatedTime());
        }

    }
}
