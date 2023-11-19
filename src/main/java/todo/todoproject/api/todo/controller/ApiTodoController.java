package todo.todoproject.api.todo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import todo.todoproject.api.todo.dto.TodoWriteDto;
import todo.todoproject.api.todo.service.ApiTodoService;
import todo.todoproject.global.util.JwtUtil;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
public class ApiTodoController {

    private final ApiTodoService apiTodoService;

    @PostMapping()
    public ResponseEntity<?> writeTodo(HttpServletRequest req, @Valid @RequestBody TodoWriteDto todoWriteDto) {

        String accessToken = JwtUtil.getTokenFromRequest(req);

        TodoWriteDto savedTodo = apiTodoService.writeTodo(accessToken, todoWriteDto);

        return ResponseEntity.ok(savedTodo);
    }

}
