package toyPJT.toypjt_v100.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyPJT.toypjt_v100.controller.MemberController;
import toyPJT.toypjt_v100.domain.Member;
import toyPJT.toypjt_v100.repository.MemberRepository;
import toyPJT.toypjt_v100.repository.MemberRepositoryImpl;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService{

    private final MemberRepository memberRepository;
    private final MemberRepositoryImpl memberRepositoryImpl;
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

    //회원가입 시 유효성검증
    public void validationUserInfo(String username) {
        Member byUsername = memberRepositoryImpl.findByUsername(username);
        if(byUsername != null) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }
    }

    public Member getLoggedInMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Member) {
            Member member = (Member) authentication.getPrincipal();
            return member; // 로그인된 사용자 ID 반환
        }
        return null; // 로그인되지 않은 상태
    }
}
