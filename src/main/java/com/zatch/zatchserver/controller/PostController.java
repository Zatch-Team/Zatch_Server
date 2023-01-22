package com.zatch.zatchserver.controller;


import com.zatch.zatchserver.ErrorHandler.ApiResponse;
import com.zatch.zatchserver.domain.Post;
import com.zatch.zatchserver.dto.PostPostReqDto;
import com.zatch.zatchserver.service.PostService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;


    @ResponseBody
    @PostMapping("/new")
    @ApiOperation(value = "재치 등록", notes = "재치등록 API")
    public ApiResponse postPost(@RequestBody PostPostReqDto postPostReqDto) {

        Post.PostBuilder postBuilder = Post.builder()
                .user_id(postPostReqDto.getUser_id())
                .category_id(postPostReqDto.getCategory_id())
                .is_free(postPostReqDto.getIs_free())
                .item_name(postPostReqDto.getItem_name())
                .content(postPostReqDto.getContent())
                .quantity(postPostReqDto.getQuantity())
                .date_buy(postPostReqDto.getDate_buy())
                .date_expire(postPostReqDto.getDate_expire())
                .is_opened(postPostReqDto.getIs_opened())
                .any_zatch(postPostReqDto.getAny_zatch());


        Post post=PostService.write(postBuilder);

        return postService.findByIdWithLike(post);
    }
}

