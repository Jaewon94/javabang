package com.dbjava.javabang.controller;

import com.dbjava.javabang.domain.entity.Accommodation;
import com.dbjava.javabang.domain.entity.AccommodationCategory;
import com.dbjava.javabang.domain.entity.User;
import com.dbjava.javabang.service.AccommodationService;
import com.dbjava.javabang.service.UserService;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

  private final AccommodationService accommodationService;
  private final UserService userService;

  @GetMapping("/")
  public String index(Model model, Principal principal) {

    List<Accommodation> accommodations = accommodationService.findAll();

    if (principal != null) {
      String username = principal.getName();
      User user = userService.findByUsername(username);

      Map<Long, Boolean> wishlistStatus = new HashMap<>();
      for (Accommodation accommodation : accommodations) {
        wishlistStatus.put(accommodation.getId(), accommodationService.isInWishlist(user, accommodation));
      }
      model.addAttribute("wishlistStatus", wishlistStatus);
      model.addAttribute("isLoggedIn", true);
    } else {
      model.addAttribute("isLoggedIn", false);
    }


    model.addAttribute("categories", AccommodationCategory.values());
    model.addAttribute("accommodations", accommodations);

    return "index";
  }

  // 로그인
  @GetMapping("/login")
  public String login() {
    return "user/login";
  }


}
