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
    log.debug("1111");
    log.info("2222");
    log.error("3333");
    log.trace("4444");
    log.warn("5555");
    return "index";
  }

}
