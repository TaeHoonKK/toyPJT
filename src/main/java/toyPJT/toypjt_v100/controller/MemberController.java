package toyPJT.toypjt_v100.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toyPJT.toypjt_v100.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(SignupRequest request){
        memberService.signup(request.getUsername(), request.getPassword());
        return ResponseEntity.ok("회원가입 성공");

    }

    @Getter @Setter
    static class SignupRequest{
        private String username;
        private String password;
    }
}
