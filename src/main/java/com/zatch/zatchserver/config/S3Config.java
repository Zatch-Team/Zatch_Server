package com.zatch.zatchserver.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class S3Config {
    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

//    @Bean
//    @Primary
//    public BasicAWSCredentials awsCredentialsProvider(){
//        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, secretKey);
//        return basicAWSCredentials;
//    }

    @Bean
    public AmazonS3 amazonS3Client() {
        System.out.println("KEY"+secretKey);
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
//        AmazonS3 s3Builder = AmazonS3ClientBuilder
//                .standard()
//                .withRegion(region)
//                .withCredentials(new AWSStaticCredentialsProvider(awsCredentialsProvider()))
//                .build();
//        return s3Builder;

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
    }
}
