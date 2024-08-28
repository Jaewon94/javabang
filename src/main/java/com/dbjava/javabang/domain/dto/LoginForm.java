package com.dbjava.javabang.domain.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class LoginForm {

  private String username;

  @Size(min = 8, max = 12, message = "비밀번호는 8~12자로 입력하세요")
  private String password;

}
