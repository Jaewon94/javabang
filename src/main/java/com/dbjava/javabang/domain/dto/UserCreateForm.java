package com.dbjava.javabang.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserCreateForm {


  @NotBlank(message = "아이디를 입력하세요.")
  @Size(min = 4, max = 20, message = "아이디는 4자 이상 20자 이하로 입력하세요.")
  private String username;

  @NotBlank(message = "비밀번호를 입력하세요.")
  @Size(min = 6, max = 20, message = "비밀번호는 최소 6자 이상, 최대 20자 이하 입력하세요.")
  private String password;

  @NotBlank(message = "이름을 입력하세요.")
  private String fullName;

  @NotBlank(message = "닉네임을 입력하세요.")
  @Size(max = 10, message = "닉네임은 10자 이하로 입력하세요.")
  private String nickname;

  @Email(message = "이메일 형식으로 입력하세요.")
  private String email;

  private String birthdate;

  @Pattern(regexp = "^\\d{10,15}$", message = "전화번호는 10~15자리 숫자로 입력하세요. ex) 010-1111-2222")
  private String phone;

  @NotBlank(message = "성별을 선택하세요.")
  private String gender;


}
