package com.dbjava.javabang.service;

import com.dbjava.javabang.domain.dto.UserCreateForm;
import com.dbjava.javabang.domain.entity.User;
import com.dbjava.javabang.respository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public void register(@Valid UserCreateForm dto) {

    userRepository.findByUsername(dto.getUsername()).ifPresent(user -> {
      throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
    });

    userRepository.findByEmail(dto.getEmail()).ifPresent(user -> {
      throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
    });

    userRepository.findByNickname(dto.getNickname()).ifPresent(user -> {
      throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
    });

    // Create a new user and save it to the database
    User user = new User(dto);

    System.out.println("user = " + user);

    user.setPassword(passwordEncoder.encode(dto.getPassword())); // password encryption
    userRepository.save(user);
  }

  public User findById(Long userId) {
    return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found."));
  }
}
