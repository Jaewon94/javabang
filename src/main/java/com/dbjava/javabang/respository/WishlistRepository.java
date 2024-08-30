package com.dbjava.javabang.respository;

import com.dbjava.javabang.domain.entity.Accommodation;
import com.dbjava.javabang.domain.entity.User;
import com.dbjava.javabang.domain.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

  void deleteByUserAndAccommodation(User user, Accommodation accommodation);

  boolean existsByUserAndAccommodation(User user, Accommodation accommodation);
}
