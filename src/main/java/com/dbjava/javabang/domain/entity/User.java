package com.dbjava.javabang.domain.entity;

import com.dbjava.javabang.domain.dto.UserCreateForm;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "users")
@ToString
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String username;
  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String fullName;
  @Column(nullable = false, unique = true)
  private String nickname;
  @Column(nullable = false)
  private String email;
  @Column(nullable = false)
  private LocalDate birthDate;
  @Column(nullable = false)
  private String phone;

  @Column
  private String profileImageUrl = "http://localhost:8080/images/basicProfile.png";

  @Enumerated(EnumType.STRING)
  private Gender gender;

  public User(@Valid UserCreateForm dto) {
    this.username = dto.getUsername();
    this.password = dto.getPassword();
    this.fullName = dto.getFullName();
    this.nickname = dto.getNickname();
    this.email = dto.getEmail();
    this.birthDate = LocalDate.parse(dto.getBirthdate());
    this.phone = dto.getPhone();

    if (dto.getGender().equals("male")) {
      this.gender = Gender.MALE;
    } else if (dto.getGender().equals("female")) {
      this.gender = Gender.FEMALE;
    }
  }
}
