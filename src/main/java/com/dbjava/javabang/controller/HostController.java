package com.dbjava.javabang.controller;

import com.dbjava.javabang.domain.dto.RequestAccommodation;
import com.dbjava.javabang.domain.entity.Accommodation;
import com.dbjava.javabang.domain.entity.User;
import com.dbjava.javabang.service.AccommodationService;
import com.dbjava.javabang.service.UserService;
import jakarta.servlet.ServletContext;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/host")
public class HostController {

  private final AccommodationService accommodationService;
  private final UserService userService;
  private final ServletContext servletContext;


  @GetMapping("/rentManage/{id}")
  public String managePage(Model model, @PathVariable("id") Long userId) {

    List<Accommodation> accommodations = accommodationService.findAllByUserId(userId);

    model.addAttribute("accommodations", accommodations);
    model.addAttribute("userId", userId);

    return "rent/rentManage";
  }

  @GetMapping("/{userId}/create")
  public String createAccommodation(@PathVariable("userId") Long userId, Model model) {

    List<String> categories = Arrays.asList("펜션", "풀빌라", "호텔", "리조트", "글램핑", "캠핑", "게스트하우스", "한옥", "료칸");
    List<String> roomTypes = Arrays.asList("독채", "원룸", "멀티룸");
    List<String> items = Arrays.asList("room", "bathroom", "guest", "bed");
    List<String> amenities = Arrays.asList("무선 인터넷", "TV", "컴퓨터", "주방", "세탁기", "에어컨", "주변 무료 주차", "주변 유료 주차",
        "수영장", "욕조", "바베큐 그릴", "피아노", "키즈", "빔프로젝트", "무료영화(OTT)", "운동기구",
        "계곡과 인접", "해변과 인접", "화재경보기", "구급상자", "소화기");
    List<String> sido = Arrays.asList("강원특별자치도", "경기도", "경상남도", "경상북도", "광주광역시", "대구광역시", "대전광역시", "부산광역시", "서울특별시", "세종특별자치시", "울산광역시", "인천광역시", "전라남도", "전라북도", "제주특별자치도", "충청남도", "충청북도");

    model.addAttribute("categories", categories);
    model.addAttribute("roomTypes", roomTypes);
    model.addAttribute("items", items);
    model.addAttribute("amenities", amenities);
    model.addAttribute("sido", sido);


    return "rent/create";
  }

  @PostMapping("/{userId}/create")
  public String createAccommodation(@ModelAttribute RequestAccommodation dto, @PathVariable("userId") Long userId) {

    accommodationService.saveImages(dto, userId);

    User findUser = userService.findById(userId);
    Accommodation accommodation = new Accommodation(dto, findUser);

    accommodationService.save(accommodation);

    return  "redirect:/host/rentManage/"+userId;
  }


}
