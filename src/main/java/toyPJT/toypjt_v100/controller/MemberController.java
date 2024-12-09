package toyPJT.toypjt_v100.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import toyPJT.toypjt_v100.service.MemberService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/user")
    public ResponseEntity<Map<String, Object>> signup(@RequestBody SignupRequest request){

        if(!StringUtils.hasLength(request.getUsername()) || !StringUtils.hasLength(request.getPassword())
        || !StringUtils.hasLength(request.getNickname())){
            Map<String, Object> result = new HashMap<>();
            result.put("resultMessage", "정보를 모두 입력해주세요.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        try {
            //회원가입 시 아이디에 대한 중복여부 체크
            memberService.validationUserInfo(request.getUsername());

            memberService.signup(request.getUsername(), request.getNickname(), request.getPassword());
        } catch(Exception ex){
            Map<String, Object> result = new HashMap<>();
            result.put("resultMessage", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        return ResponseEntity.ok().build();

    }

    @Getter @Setter
    static class SignupRequest{
        private String username;
        private String nickname;
        private String password;
    }
}
