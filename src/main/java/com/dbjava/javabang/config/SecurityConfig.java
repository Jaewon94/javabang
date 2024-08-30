package com.dbjava.javabang.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();  // 비밀번호 해싱에 BCrypt 사용
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(authorizeRequests ->
            authorizeRequests
                .requestMatchers("/", "/login", "/host/**", "/user/**", "/**").permitAll() // /public 아래의 URL에 대한 공개 액세스를 허용합니다.
                .anyRequest().authenticated() // 기타 모든 요청에는 인증이 필요합니다.
        )
        .csrf((csrf) -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/**"))) // CSRF(Cross-Site Request Forgery) 방지)
        .formLogin(formLogin ->
            formLogin
                .loginPage("/login") // 사용자 정의 로그인 페이지
                .defaultSuccessUrl("/") // 로그인 성공 시 이동할 URL
                .failureUrl("/login?error=true") // 로그인 실패 시 이동할 URL
                .permitAll()
        )
        .logout(logout ->
            logout
                .logoutUrl("/logout") // 로그아웃 URL
                .logoutSuccessUrl("/") // 로그아웃 성공 시 이동할 URL
                .deleteCookies("JSESSIONID")  // 세션 쿠키 삭제
                .invalidateHttpSession(true) // HttpSession invalidate
                .permitAll()
        );

    return http.build();
  }


  /**
   * 이 방법은 정적 리소스를 보안에서 제외하도록 Spring Security를 구성합니다.
   *
   * @return 정적 리소스에 대한 요청을 무시하는 {@link WebSecurityCustomizer} 인스턴스를 반환합니다.
   */
  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return web -> web.ignoring().requestMatchers(
        PathRequest.toStaticResources().atCommonLocations()
    );
  }



}
