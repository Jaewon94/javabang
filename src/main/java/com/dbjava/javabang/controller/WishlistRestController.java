package com.dbjava.javabang.controller;

import com.dbjava.javabang.domain.entity.Accommodation;
import com.dbjava.javabang.domain.entity.User;
import com.dbjava.javabang.service.AccommodationService;
import com.dbjava.javabang.service.UserService;
import com.dbjava.javabang.service.WishlistService;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
public class WishlistRestController {

  private final WishlistService wishlistService;
  private final UserService userService;
  private final AccommodationService accommodationService;

  @PostMapping("/add/{accommodationId}")
  public ResponseEntity<?> addToWishlist(@PathVariable("accommodationId") Long accommodationId, Principal principal) {

    try {
      User user = userService.getUserByUsername(principal.getName());
      Accommodation accommodation = accommodationService.findById(accommodationId);

      wishlistService.addToWishlist(user, accommodation);

      Map<String, Object> response = new HashMap<>();
      response.put("success", true);
      response.put("message", "Successfully added to wishlist");

      return ResponseEntity.ok(response);
    } catch (Exception e) {
      Map<String, Object> response = new HashMap<>();
      response.put("success", false);
      response.put("message", "Failed to add to wishlist: " + e.getMessage());

      return ResponseEntity.badRequest().body(response);
    }
  }

  @PostMapping("/remove/{accommodationId}")
  public ResponseEntity<?> removeFromWishlist(@PathVariable("accommodationId") Long accommodationId, Principal principal) {
    try {
      User user = userService.getUserByUsername(principal.getName());
      Accommodation accommodation = accommodationService.findById(accommodationId);

      wishlistService.removeFromWishlist(user, accommodation);

      Map<String, Object> response = new HashMap<>();
      response.put("success", true);
      response.put("message", "Successfully removed from wishlist");

      return ResponseEntity.ok(response);
    } catch (Exception e) {
      Map<String, Object> response = new HashMap<>();
      response.put("success", false);
      response.put("message", "Failed to remove from wishlist: " + e.getMessage());

      return ResponseEntity.badRequest().body(response);
    }
  }

  @GetMapping("/status/{accommodationId}")
  public ResponseEntity<?> getWishlistStatus(@PathVariable Long accommodationId,
      Principal principal) {
    try {
      User user = userService.getUserByUsername(principal.getName());
      Accommodation accommodation = accommodationService.findById(accommodationId);

      boolean isInWishlist = wishlistService.isInWishlist(user, accommodation);

      Map<String, Object> response = new HashMap<>();
      response.put("success", true);
      response.put("isInWishlist", isInWishlist);

      return ResponseEntity.ok(response);
    } catch (Exception e) {
      Map<String, Object> response = new HashMap<>();
      response.put("success", false);
      response.put("message", "Failed to get wishlist status: " + e.getMessage());

      return ResponseEntity.badRequest().body(response);
    }
  }

}
