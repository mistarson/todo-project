package todo.todoproject.domain.todo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import todo.todoproject.domain.todo.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query("select t from Todo t join fetch t.member where t.todoId = :todoId")
    Optional<Todo> findByTodoIdWithMember(@Param("todoId") Long todoId);
}
