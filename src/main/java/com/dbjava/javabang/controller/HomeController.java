package com.dbjava.javabang.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

  @GetMapping("/")
  public String index() {
    return "index";
  }

  // 로그인
  @GetMapping("/login")
  public String login() {
    return "user/login";
  }


}
