package com.dbjava.javabang.controller;

import com.dbjava.javabang.domain.dto.LoginForm;
import com.dbjava.javabang.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/login")
  public String login() {
    return "user/login";
  }

//
//  @PostMapping("/login")
//  public String loginPost(@Valid LoginForm form, BindingResult bindingResult) {
//
//    if (bindingResult.hasErrors()) {
//      // validation error
//      return "user/login";
//    }
////    userService.login(form);
//    // 로그인 성공 시
//    return "redirect:/";
//  }

}
