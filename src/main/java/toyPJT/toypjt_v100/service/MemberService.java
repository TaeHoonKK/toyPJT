package toyPJT.toypjt_v100.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyPJT.toypjt_v100.domain.Member;
import toyPJT.toypjt_v100.repository.MemberRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;  // PasswordEncoder 주입

    public void signup(String username, String password){
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(password);

        // 회원가입
        Member member = new Member();
        member.setUsername(username);
        member.setPassword(encodedPassword);

        memberRepository.save(member);
    }
}
