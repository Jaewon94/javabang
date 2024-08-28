package com.dbjava.javabang.domain.dto;

import java.util.Collection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
@Getter
@Setter
public class CustomUserDetails implements UserDetails {

  private final Long id; // Add your custom property
  private final String username;
  private final String password;
  private final Collection<? extends GrantedAuthority> authorities;
  private final String profileImageUrl;
  private final String nickname;

}
