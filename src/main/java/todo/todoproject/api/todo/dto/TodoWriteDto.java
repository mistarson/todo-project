package todo.todoproject.api.todo.dto;

import jakarta.validation.constraints.NotBlank;
import todo.todoproject.domain.member.entity.Member;
import todo.todoproject.domain.todo.entity.Todo;

public record TodoWriteDto(
        @NotBlank String todoTitle,
        @NotBlank String todoContent) {

    public Todo toEntity(Member member) {
        return Todo.builder()
                .todoTitle(todoTitle)
                .todoContent(todoContent)
                .isCompleted(false)
                .isPrivate(false)
                .member(member)
                .build();
    }

    public static TodoWriteDto from(Todo todo) {
        return new TodoWriteDto(todo.getTodoTitle(), todo.getTodoContent());
    }
}
