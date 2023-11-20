package todo.todoproject.domain.todo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import todo.todoproject.domain.common.BaseTimeEntity;
import todo.todoproject.domain.member.entity.Member;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;

    @Column(nullable = false)
    private String todoTitle;

    @Column(nullable = false)
    private String todoContent;

    @Column(nullable = false)
    private boolean isCompleted;

    @Column(nullable = false)
    private boolean isPrivate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;

    public void modify(Todo modifyTodo) {
        this.todoTitle = modifyTodo.getTodoTitle();
        this.todoContent = modifyTodo.getTodoContent();
    }

    public void completeTodo() {
        this.isCompleted = true;
    }
}
