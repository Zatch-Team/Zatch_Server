package com.zatch.zatchserver.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfig {  // Swagger

    private static final String API_NAME = "ZATCH_Server API";
    private static final String API_VERSION = "0.0.1";
    private static final String API_DESCRIPTION = "ZATCH_Server API 명세서";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securitySchemes(Arrays.asList(apiKey())) // swagger에서 jwt 토큰값 넣기위한 설정
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zatch.zatchserver"))  // Swagger를 적용할 클래스의 package명
                .paths(PathSelectors.any())  // 해당 package 하위에 있는 모든 url에 적용
                .build()
                .apiInfo(apiInfo());
    }

    public ApiInfo apiInfo() {  // API의 이름, 현재 버전, API에 대한 정보
        return new ApiInfoBuilder()
                .title(API_NAME)
                .version(API_VERSION)
                .description(API_DESCRIPTION)
                .build();
    }

    // authorization jwt
    private ApiKey apiKey() {
        return new ApiKey("JWT", "jwt", "header");
    }

}
