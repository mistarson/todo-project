package todo.todoproject.domain.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todo.todoproject.domain.todo.entity.Todo;
import todo.todoproject.domain.todo.repository.TodoRepository;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public Todo saveTodo(Todo todo) {
        return todoRepository.save(todo);
    }
}
