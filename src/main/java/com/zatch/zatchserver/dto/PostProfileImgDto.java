package com.zatch.zatchserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
@ApiModel(description = "프로필 업로드")
public class PostProfileImgDto {
    @ApiModelProperty(value = "프로필 이미지 url", example = "https://아마존.이미지예시.png")
    private MultipartFile image;
}
