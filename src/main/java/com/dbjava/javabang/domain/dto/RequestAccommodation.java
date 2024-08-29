package com.dbjava.javabang.domain.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
@ToString
public class RequestAccommodation {

  private String category;

  private String roomType;

  private Integer roomCount;
  private Integer bathroomCount;
  private Integer guestCount;
  private Integer bedCount;

  private List<String> amenities;

  private MultipartFile mainImageUrl;
  private String mainImagePath;
  private List<MultipartFile> subImagesUrls;
  private List<String> subImagePaths;

  private String title;
  private Integer price;
  private String contactNum;
  private String description;

  private String sigugun;
  private String gueup;
  private String road;
  private String detailAddress;

}
