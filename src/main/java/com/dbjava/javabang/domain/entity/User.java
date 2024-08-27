package com.dbjava.javabang.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "users")
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



}
