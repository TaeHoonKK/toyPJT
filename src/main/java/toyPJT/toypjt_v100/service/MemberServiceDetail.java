package toyPJT.toypjt_v100.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import toyPJT.toypjt_v100.domain.Member;
import toyPJT.toypjt_v100.repository.MemberRepositoryImpl;

@RequiredArgsConstructor
@Service
public class MemberServiceDetail implements UserDetailsService{

    private final MemberRepositoryImpl memberRepository;

    // 사용자 이름으로 사용자의 정보를 가져오는 메서드
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username);
        if(member == null) {
            throw new UsernameNotFoundException("User not found : " + username);
        }

        return member;
    }
}
