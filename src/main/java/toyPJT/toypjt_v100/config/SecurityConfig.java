package toyPJT.toypjt_v100.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // 빈 비밀번호 암호화를 위한 빈
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // SecurityFilterChain을 사용한 보안 설정
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // CSRF 비활성화 (필요한 경우 활성화)
                .authorizeRequests()
                .requestMatchers("/login", "/signup", "/user").permitAll() // 로그인 페이지와 정적 리소스 허용
                .anyRequest().authenticated() // 나머지 요청은 인증 필요
                .and()
                .formLogin()
                .loginPage("/login") // 로그인 페이지 URL 명시
                .defaultSuccessUrl("/", true) // 로그인 성공 시 리다이렉트
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login"); // 로그아웃 성공 시 리다이렉트

        return http.build();
    }
}
