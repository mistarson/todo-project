package todo.todoproject.api.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import todo.todoproject.domain.member.entity.Member;

@Getter
public class MemberRegisterDto {

    private static final String REGEX_MEMBER_NAME_PATTERN = "[a-z|0-9]{4,10}";
    private static final String REGEX_PASSWORD_PATTERN = "[a-z|A-Z|0-9]{8,15}";

    @Size(min = 4, max = 10)
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @Pattern(regexp = REGEX_MEMBER_NAME_PATTERN)
    private String memberName;

    @Size(min = 8, max = 15)
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = REGEX_MEMBER_NAME_PATTERN)
    private String password;

    public Member toEntity() {
        return Member.builder()
                .memberName(memberName)
                .password(password)
                .build();
    }

}
