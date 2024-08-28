package com.dbjava.javabang.controller;

import com.dbjava.javabang.domain.dto.UserCreateForm;
import com.dbjava.javabang.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  private final UserService userService;


  // 회원가입
  @GetMapping("/register")
  public String register() {
    return "user/register";
  }

  @PostMapping("/register")
  public String register(@Valid UserCreateForm dto, BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("errors", bindingResult.getAllErrors());
      return "user/register";
    }

    try {
      userService.register(dto);
    } catch (IllegalArgumentException e) {
      bindingResult.rejectValue("username", "error.user", e.getMessage());
      model.addAttribute("errors", bindingResult.getAllErrors());
      return "user/register";
    }
    return "redirect:/login";
  }

}
