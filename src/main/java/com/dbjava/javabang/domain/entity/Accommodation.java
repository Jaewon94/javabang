package com.dbjava.javabang.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Getter @Setter
@Table(name = "accommodations")
public class Accommodation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @OneToMany(mappedBy = "accommodation", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<AccommodationImage> images = new ArrayList<>();


// 대분류 (호텔, 모텔, etc)
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private AccommodationCategory category;

//  방 갯수(원룸, 투룸, ... , 멀티룸)
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private AccommodationType type;

//  방 사용상태(사용가능, 예약중, 예약완료)
  @Enumerated(EnumType.STRING)
  private AccommodationStatus status = AccommodationStatus.AVAILABLE;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String address;

  @Column(nullable = false)
  private String detailAddress;

  @Column(nullable = false)
  private Integer guestCount;

  @Column(nullable = false)
  private Integer roomCount;

  @Column(nullable = false)
  private Integer bedCount;

  @Column(nullable = false)
  private Integer bathCount;

  @Lob
  private String description;

  @Column(nullable = false)
  private Integer pricePerNight;

  @Column(nullable = false)
  private String contactNumber;

  @Column
  private Integer discountPercentage;

}
