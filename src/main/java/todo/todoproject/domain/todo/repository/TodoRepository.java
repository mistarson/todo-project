package todo.todoproject.domain.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todo.todoproject.domain.todo.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
