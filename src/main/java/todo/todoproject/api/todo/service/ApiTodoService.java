package todo.todoproject.api.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import todo.todoproject.api.todo.dto.TodoWriteDto;
import todo.todoproject.domain.member.entity.Member;
import todo.todoproject.domain.member.service.MemberService;
import todo.todoproject.domain.todo.entity.Todo;
import todo.todoproject.domain.todo.service.TodoService;
import todo.todoproject.global.exception.member.MemberNotFoundException;
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
}
