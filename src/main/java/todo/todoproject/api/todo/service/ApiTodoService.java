package todo.todoproject.api.todo.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todo.todoproject.api.todo.dto.TodoInquireDto;
import todo.todoproject.api.todo.dto.TodoModifyDto.Request;
import todo.todoproject.api.todo.dto.TodoModifyDto.Response;
import todo.todoproject.api.todo.dto.TodoWriteDto;
import todo.todoproject.api.todo.dto.TodosInquireDto;
import todo.todoproject.domain.member.entity.Member;
import todo.todoproject.domain.member.service.MemberService;
import todo.todoproject.domain.todo.entity.Todo;
import todo.todoproject.domain.todo.service.TodoService;
import todo.todoproject.global.exception.member.MemberNotFoundException;
import todo.todoproject.global.exception.todo.MisMatchedMemberException;
import todo.todoproject.global.exception.todo.PrivateTodoException;
import todo.todoproject.global.jwt.JwtManager;

@Service
@RequiredArgsConstructor
public class ApiTodoService {

    private final TodoService todoService;

    private final MemberService memberService;

    private final JwtManager jwtManager;

    public TodoWriteDto writeTodo(String accessToken, TodoWriteDto todoWriteDto) {

        String memberName = jwtManager.getMemberNameFromToken(accessToken);

        Member findMember = memberService.findMemberByMemberName(memberName)
                .orElseThrow(MemberNotFoundException::new);

        Todo savedTodo = todoService.saveTodo(todoWriteDto.toEntity(findMember));

        return TodoWriteDto.from(savedTodo);
    }

    public TodoInquireDto inquireTodo(String accessToken, Long todoId) {

        String memberName = jwtManager.getMemberNameFromToken(accessToken);
        Todo findTodo = todoService.findByTodoIdWithMember(todoId);

        validatePrivateTodo(memberName, findTodo);

        return TodoInquireDto.from(findTodo);
    }

    private void validatePrivateTodo(String memberNameFromRequest, Todo todo) {

        Member memberByTodo = todo.getMember();
        if (todo.isPrivate() && !memberByTodo.getMemberName().equals(memberNameFromRequest)) {
            throw new PrivateTodoException();
        }
    }

    public List<TodosInquireDto> inquireTodos(String accessToken) {

        String memberName = jwtManager.getMemberNameFromToken(accessToken);

        return todoService.findNotPrivateTodos().stream().map(TodosInquireDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public Response modifyTodo(String accessToken, Request todoModifyDto, Long todoId) {

        String memberName = jwtManager.getMemberNameFromToken(accessToken);

        Todo findTodo = todoService.findByTodoIdWithMember(todoId);

        Member findMember = findTodo.getMember();
        validateSameMemberTodo(memberName, findMember.getMemberName());

        Todo todo = todoService.modifyTodo(todoModifyDto.toEntity(), findTodo);

        return Response.from(todo);
    }

    private void validateSameMemberTodo(String memberNameFromRequest, String memberNameFromDB) {
        if (!memberNameFromDB.equals(memberNameFromRequest)) {
            throw new MisMatchedMemberException();
        }
    }
}
