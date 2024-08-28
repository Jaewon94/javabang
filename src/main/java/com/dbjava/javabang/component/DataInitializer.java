package com.dbjava.javabang.component;

import com.dbjava.javabang.domain.entity.Gender;
import com.dbjava.javabang.domain.entity.User;
import com.dbjava.javabang.respository.UserRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// 기본 사용자로 데이터베이스 초기화
@Component
@RequiredArgsConstructor
public class DataInitializer {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Bean
  public CommandLineRunner initializeDatabase() {
    return args -> {
      // Check if any user exists
      if (userRepository.count() == 0) {
        // Create a default user
        User adminUser = new User();
        adminUser.setUsername("admin");
        adminUser.setPassword(passwordEncoder.encode("admin12345")); // Hash the password
        adminUser.setFullName("Admin User");
        adminUser.setNickname("admin");
        adminUser.setEmail("admin@example.com");
        adminUser.setBirthDate(LocalDate.of(2000, 1, 1));
        adminUser.setPhone("010-1111-1111");
        adminUser.setGender(Gender.MALE); // Choose an appropriate gender
        // Save the default user to the database
        userRepository.save(adminUser);

        User user = new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("user12345")); // Hash the password
        user.setFullName("User user");
        user.setNickname("user");
        user.setEmail("user@example.com");
        user.setBirthDate(LocalDate.of(1990, 1, 1));
        user.setPhone("010-2222-2222");
        user.setGender(Gender.FEMALE); // Choose an appropriate gender
        // Save the user to the database
        userRepository.save(user);
      }
    };
  }

}
