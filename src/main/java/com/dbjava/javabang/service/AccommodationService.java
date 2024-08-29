package com.dbjava.javabang.service;

import com.dbjava.javabang.domain.dto.RequestAccommodation;
import com.dbjava.javabang.domain.entity.Accommodation;
import com.dbjava.javabang.respository.AccommodationRepository;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AccommodationService {

  private final AccommodationRepository accommodationRepository;

  public List<Accommodation> findAllByUserId(Long userId) {
    return accommodationRepository.findAllByUserId(userId);
  }

  public void save(Accommodation accommodation) {
    accommodationRepository.save(accommodation);
  }

  public void saveImages(RequestAccommodation dto, Long userId) {
    String roomFolderName = "user_" + userId + "_roomImgs"; // 회의실 또는 userId를 기반으로 한 폴더 이름 예시
    String basePath = new File("src/main/resources/static/images/" + roomFolderName).getAbsolutePath();

    try {
      // 디렉터리가 존재하는지 확인하세요.
      existDir(basePath);
      // 메인 이미지 저장 및 서브 이미지 저장
      saveAccomodationImgs(dto, basePath, roomFolderName);

    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("Failed to save file: " + dto.getMainImageUrl().getOriginalFilename(), e);
    }
  }

  private void saveAccomodationImgs(RequestAccommodation dto, String basePath, String roomFolderName) {
    if (dto.getMainImageUrl() != null && !dto.getMainImageUrl().isEmpty()) {
      String mainImagePath = saveFile(dto.getMainImageUrl(), basePath, "main", roomFolderName);
      dto.setMainImagePath(mainImagePath);
    }

    if (dto.getSubImagesUrls() != null && !dto.getSubImagesUrls().isEmpty()) {
      List<String> subImagePaths = dto.getSubImagesUrls().stream()
          .map(file -> saveFile(file, basePath, "sub", roomFolderName))
          .collect(Collectors.toList());

      dto.setSubImagePaths(subImagePaths);
    }
  }

  private static void existDir(String basePath) {
    File directory = new File(basePath);
    if (!directory.exists()) {
      directory.mkdirs();
    }
  }

  private String saveFile(MultipartFile file, String basePath, String type, String roomFolderName) {
    try {
      String fileName = null;
      if(type.equals("main")) {
        fileName = "main_" + System.currentTimeMillis() + "_" + file.getOriginalFilename();
      } else if (type.equals("sub")) {
        fileName = "sub_" + System.currentTimeMillis() + "_" + file.getOriginalFilename();
      }
      File targetFile = new File(basePath, fileName);
      file.transferTo(targetFile);

      // Return the relative path to the file
      return "/images/" + roomFolderName + "/" + new File(targetFile.getPath()).getName();
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Failed to save file: " + file.getOriginalFilename(), e);
    }
  }
}
