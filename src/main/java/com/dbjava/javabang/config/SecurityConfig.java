package com.dbjava.javabang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
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
                .requestMatchers("/**").permitAll() // /public 아래의 URL에 대한 공개 액세스를 허용합니다.
                .anyRequest().authenticated() // 기타 모든 요청에는 인증이 필요합니다.
        )
        .csrf((csrf) -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/**"))) // CSRF(Cross-Site Request Forgery) 방지)
        .formLogin(formLogin ->
            formLogin
                .loginPage("/login") // 사용자 정의 로그인 페이지
                .permitAll()
        )
        .logout(logout ->
            logout
                .permitAll()
        );

    return http.build();
  }

    // 기본 사용자가 있는 메모리 내 사용자 저장소
  @Bean
  public UserDetailsService userDetailsService() {
    var user = User
        .withUsername("user")
        .password(passwordEncoder().encode("user"))
        .roles("USER")
        .build();

    return new InMemoryUserDetailsManager(user);
  }

}
