package com.dbjava.javabang.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "accommodation_images")
public class AccommodationImage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "accommodation_id", nullable = false)
  private Accommodation accommodation;

  @Column(nullable = false)
  private String imageUrl;

  public AccommodationImage(Accommodation accommodation, String imagePath) {
    this.accommodation = accommodation;
    this.imageUrl = imagePath;
  }


  // 연관관계 편의 메서드
  public void setAccommodation(Accommodation accommodation) {
    this.accommodation = accommodation;

    accommodation.getImages().add(this);
  }

}
