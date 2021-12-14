package com.example.thebestmeal_test.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import com.example.thebestmeal_test.domain.User;
import com.example.thebestmeal_test.dto.PostDto;
import com.example.thebestmeal_test.repository.UserRepository;
import com.example.thebestmeal_test.security.UserDetailsImpl;
import com.example.thebestmeal_test.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class AwsService {

    private final AmazonS3Client amazonS3Client;
    private final UserService userService;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;  // S3 버킷 이름

    @Value("${cloud.aws.s3.uri}")
    public String s3Uri;  // S3 버킷 이름

    public String uploadFoodImg(MultipartFile multipartFile) throws IOException {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getBytes().length); //이미지 변환

        String originalFilename = multipartFile.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        String random = random();
        String fileName = "food" + "/" + random + extension;   // S3에 저장된 파일 이름

        InputStream inputStream = multipartFile.getInputStream();
        putS3(fileName, inputStream, objectMetadata); // s3로 업로드

        String uploadImageUrl = s3Uri + fileName; //url

        System.out.println(uploadImageUrl);
        return uploadImageUrl;
    }

    public String upload(MultipartFile multipartFile, String dirName, User user) throws IOException {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getBytes().length); //이미지 변환

        Long userId = user.getId();
        String originalFilename = multipartFile.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        String random = random();
        String fileName = dirName + "/" + userId + "_" + "profile" + "_" + random + extension;   // S3에 저장된 파일 이름

        InputStream inputStream = multipartFile.getInputStream();
        putS3(fileName, inputStream, objectMetadata); // s3로 업로드

        String uploadImageUrl = s3Uri + fileName; //url

        System.out.println(uploadImageUrl);
        userService.updateProfileImg(uploadImageUrl, user);
        return uploadImageUrl;
    }

    private String random() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    // S3로 업로드
    private void putS3(String fileName, InputStream inputStream, ObjectMetadata objectMetadata) {
        TransferManager transferManager = TransferManagerBuilder.standard().withS3Client(amazonS3Client).build();
        PutObjectRequest request = new PutObjectRequest(bucket, fileName, inputStream, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead);
        Upload upload = transferManager.upload(request);
        try {
            upload.waitForCompletion();
        } catch (AmazonClientException amazonClientException) {
            log.error(amazonClientException.getMessage());
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }



}

