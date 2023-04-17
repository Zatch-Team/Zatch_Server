package com.zatch.zatchserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZatchserverApplication {

    public static void main(String[] args) {
        // EC2 메타데이터 비활성화
        System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
        // 실행
        SpringApplication.run(ZatchserverApplication.class, args);
    }

}
