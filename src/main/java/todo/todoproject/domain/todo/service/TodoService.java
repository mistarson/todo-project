package todo.todoproject.domain.todo.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todo.todoproject.domain.todo.entity.Todo;
import todo.todoproject.domain.todo.repository.TodoRepository;
import todo.todoproject.global.exception.todo.TodoNotFoundException;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public Todo saveTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo findByTodoIdWithMember(Long todoId) {
        return todoRepository.findByTodoIdWithMember(todoId).orElseThrow(TodoNotFoundException::new);
    }

    public List<Todo> findNotPrivateTodos() {
        return todoRepository.findNotPrivateTodos();
    }


    public Todo modifyTodo(Todo modifyTodo, Todo findTodo) {

        findTodo.modify(modifyTodo);

        return findTodo;
    }

    public void completeTodo(Todo findTodo) {
        findTodo.completeTodo();
    }
}
