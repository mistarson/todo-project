package todo.todoproject.api.login.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import todo.todoproject.api.login.dto.MemberLoginDto;
import todo.todoproject.api.login.service.ApiLoginService;

@RestController
@RequiredArgsConstructor
public class ApiLoginController {

    private final ApiLoginService apiLoginService;

    @PostMapping("/login")
    public ResponseEntity loginMember(@Valid @RequestBody MemberLoginDto memberLoginDto) {

        apiLoginService.loginMember(memberLoginDto);

        return new ResponseEntity(HttpStatus.OK);
    }

}

