package com.dbjava.javabang.respository;

import com.dbjava.javabang.domain.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

  Optional<User> findByUsername(String username);

  Optional<Object> findByEmail(String email);

  Optional<Object> findByNickname(String nickname);
}
