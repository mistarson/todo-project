package todo.todoproject.global.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import todo.todoproject.domain.member.entity.Member;
import todo.todoproject.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String memberName) throws UsernameNotFoundException {
        Member member = memberRepository.findMemberByMemberName(memberName)
                .orElseThrow(() -> new UsernameNotFoundException("Not Found " + memberName));

        return new UserDetailsImpl(member);
    }
}
