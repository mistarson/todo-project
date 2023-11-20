package todo.todoproject.api.todo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import todo.todoproject.api.todo.dto.TodoInquireDto;
import todo.todoproject.api.todo.dto.TodoModifyDto;
import todo.todoproject.api.todo.dto.TodoWriteDto;
import todo.todoproject.api.todo.dto.TodosInquireDto;
import todo.todoproject.api.todo.service.ApiTodoService;
import todo.todoproject.domain.member.entity.Member;
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

    @GetMapping("/{todoId}")
    public ResponseEntity<?> inquireTodo(HttpServletRequest req, @PathVariable Long todoId) {

        String accessToken = JwtUtil.getTokenFromRequest(req);

        TodoInquireDto todo = apiTodoService.inquireTodo(accessToken, todoId);

        return ResponseEntity.ok(todo);
    }

    @GetMapping()
    public ResponseEntity<?> inquireTodoList(@AuthenticationPrincipal Member member, HttpServletRequest req) {

        String accessToken = JwtUtil.getTokenFromRequest(req);

        List<TodosInquireDto> todos = apiTodoService.inquireTodos(accessToken);

        return ResponseEntity.ok(todos);
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<?> modifyTodo(HttpServletRequest req, @PathVariable Long todoId,
                                        @Valid @RequestBody TodoModifyDto.Request todoModifyDto) {

        String accessToken = JwtUtil.getTokenFromRequest(req);

        TodoModifyDto.Response todo = apiTodoService.modifyTodo(accessToken, todoModifyDto, todoId);

        return ResponseEntity.ok(todo);
    }

    @PostMapping("/{todoId}")
    public ResponseEntity<?> completeTodo(HttpServletRequest req, @PathVariable Long todoId) {

        String accessToken = JwtUtil.getTokenFromRequest(req);

        apiTodoService.completeTodo(accessToken, todoId);

        return new ResponseEntity(HttpStatus.OK);
    }

}
