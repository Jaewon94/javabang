package com.dbjava.javabang.service;

import com.dbjava.javabang.domain.dto.CustomUserDetails;
import com.dbjava.javabang.domain.entity.User;
import com.dbjava.javabang.respository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Optional<User> byUsername = userRepository.findByUsername(username);

    if (byUsername.isEmpty()) {
      throw new UsernameNotFoundException("사용자 이름을 가진 사용자를 찾을 수 없습니다: " + username);
    }

    User user = byUsername.get();

    List<GrantedAuthority> authorities = new ArrayList<>();
    if ("admin".equals(user.getUsername())) {
      authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
    } else {
      authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
    }

    return new CustomUserDetails(user.getId(), user.getUsername(), user.getPassword(), authorities, user.getProfileImageUrl(), user.getNickname());
  }
}
