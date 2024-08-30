package com.dbjava.javabang.service;

import com.dbjava.javabang.domain.entity.Accommodation;
import com.dbjava.javabang.domain.entity.User;
import com.dbjava.javabang.domain.entity.Wishlist;
import com.dbjava.javabang.respository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class WishlistService {

  private final WishlistRepository wishlistRepository;
  private final AccommodationService accommodationService;

  public void addToWishlist(User user, Accommodation accommodation) {
    if (!accommodationService.isInWishlist(user, accommodation)) {
      wishlistRepository.save(new Wishlist(user, accommodation));
    }
  }

  public void removeFromWishlist(User user, Accommodation accommodation) {
    wishlistRepository.deleteByUserAndAccommodation(user, accommodation);
  }

  public boolean isInWishlist(User user, Accommodation accommodation) {
    return wishlistRepository.existsByUserAndAccommodation(user, accommodation);
  }
}
