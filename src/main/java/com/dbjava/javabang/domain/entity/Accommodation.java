package com.dbjava.javabang.domain.entity;

import com.dbjava.javabang.domain.dto.RequestAccommodation;
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
import java.util.Arrays;
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
  @Column(nullable = false, length = 20)
  private AccommodationCategory category;

//  방 갯수(원룸, 투룸, ... , 멀티룸)
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private AccommodationType type;

//  방 사용상태(사용가능, 예약중, 예약완료)
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
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
  private Integer discountPercentage = 0;


  public Accommodation(RequestAccommodation dto, User findUser) {
    this.user = findUser;

    // 이미지
    this.images.add(new AccommodationImage(this, dto.getMainImagePath()));
    for (String subImagePath : dto.getSubImagePaths()) {
      this.images.add(new AccommodationImage(this, subImagePath));
    }

    // 카테고리
    String category = dto.getCategory();
    matchCategory(category);

    // 방 유형
    String type = dto.getRoomType();
    matchType(type);

    this.title = dto.getTitle();
    this.address = dto.getSigugun() + " " + dto.getGueup() + " " + dto.getRoad();
    this.detailAddress = dto.getDetailAddress();
    this.guestCount = dto.getGuestCount();
    this.roomCount = dto.getRoomCount();
    this.bedCount = dto.getBedCount();
    this.bathCount = dto.getBathroomCount();
    this.description = dto.getDescription();
    this.pricePerNight = dto.getPrice();
    this.contactNumber = dto.getContactNum();
  }

  private void matchType(String type) {
    if ("원룸".equals(type)) {
      this.type = AccommodationType.원룸;
    } else if ("멀티룸".equals(type)) {
      this.type = AccommodationType.멀티룸;
    } else if ("독채".equals(type)) {
      this.type = AccommodationType.독채;
    }
  }

  private void matchCategory(String category) {
    if ("펜션".equals(category)) {
      this.category = AccommodationCategory.펜션;
    } else if ("풀빌라".equals(category)) {
      this.category = AccommodationCategory.풀빌라;
    } else if ("호텔".equals(category)) {
      this.category = AccommodationCategory.호텔;
    } else if ("리조트".equals(category)) {
      this.category = AccommodationCategory.리조트;
    } else if ("글램핑".equals(category)) {
      this.category = AccommodationCategory.글램핑;
    } else if ("캠핑".equals(category)) {
      this.category = AccommodationCategory.캠핑;
    } else if ("게스트하우스".equals(category)) {
      this.category = AccommodationCategory.게스트하우스;
    } else if ("한옥".equals(category)) {
      this.category = AccommodationCategory.한옥;
    } else if ("료칸".equals(category)) {
      this.category = AccommodationCategory.료칸;
    }
  }
}
