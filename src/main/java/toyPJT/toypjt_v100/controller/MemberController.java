package toyPJT.toypjt_v100.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import toyPJT.toypjt_v100.service.MemberService;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/user")
    public String signup(SignupRequest request){
        memberService.signup(request.getUsername(), request.getPassword());
        return "redirect:/login";

    }

    @Getter @Setter
    static class SignupRequest{
        private String username;
        private String password;
    }
}
