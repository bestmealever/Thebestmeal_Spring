package com.example.thebestmeal_test.s3;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AmazonS3Config {

    //    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey = "AKIAZBAKZL3E3PMXSVUM";

    //    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey = "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";

    //    @Value("${cloud.aws.region.static}")
    private String region = "ap-northeast-2";

    @Bean
    public AmazonS3Client amazonS3Client() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
    }
}