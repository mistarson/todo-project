package todo.todoproject.api.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import todo.todoproject.api.member.dto.MemberRegisterDto;
import todo.todoproject.api.member.service.ApiMemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class ApiMemberController {

    private final ApiMemberService apiMemberService;

    @PostMapping("/sign-up")
    public ResponseEntity registerMember(@Valid @RequestBody MemberRegisterDto memberRegisterDto) {

        apiMemberService.registerMember(memberRegisterDto);

        return new ResponseEntity(HttpStatus.OK);
    }
}
