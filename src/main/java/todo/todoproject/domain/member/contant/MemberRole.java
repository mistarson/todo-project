package todo.todoproject.domain.member.contant;

import lombok.Getter;

@Getter
public enum MemberRole {
    USER("user"), ADMIN("admin");

    private final String authority;

    MemberRole(String authority) {
        this.authority = authority;
    }
}
