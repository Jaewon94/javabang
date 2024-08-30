package com.dbjava.javabang.respository;

import com.dbjava.javabang.domain.entity.Accommodation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

  List<Accommodation> findAllByUserId(Long userId);
}
