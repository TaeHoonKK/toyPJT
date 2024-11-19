package toyPJT.toypjt_v100.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    // 빈 비밀번호 암호화를 위한 빈
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // SecurityFilterChain을 사용한 보안 설정
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화 (필요에 따라 활성화 가능)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/members/signup", "/api/login", "/").permitAll() // 회원가입 및 로그인 URL은 인증 없이 허용
                        .anyRequest().authenticated() // 나머지 요청은 인증 필요
                )
                .formLogin(form -> form
                        .loginPage("/api/login") // 로그인 페이지 URL
                        .defaultSuccessUrl("/api/todos", true) // 로그인 성공 시 리다이렉트 URL
                )
                .logout(logout -> logout
                        .logoutUrl("/api/logout") // 로그아웃 URL
                        .logoutSuccessUrl("/api/login") // 로그아웃 성공 후 리다이렉트 URL
                );
        return http.build();
    }
}
