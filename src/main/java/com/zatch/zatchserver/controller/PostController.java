package com.zatch.zatchserver.controller;


import com.zatch.zatchserver.domain.Post;
import com.zatch.zatchserver.dto.PostPostReqDto;
import com.zatch.zatchserver.dto.PostPostResDto;
import com.zatch.zatchserver.service.PostService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    //메인페이지 조회 리스트
    @GetMapping("/post")
    public List<Post> getPost(){
        return postService.getPostList();

    }

    @PostMapping("/new")
    @ApiOperation(value = "재치 등록", notes = "재치등록 API")
    public PostPostResDto postPost(@RequestBody PostPostReqDto postPostReqDto) {
        Post newPost = new Post(
                postPostReqDto.getUser_id(),
                postPostReqDto.getCategory_id(),
                postPostReqDto.getIs_free(),
                postPostReqDto.getItem_name(),
                postPostReqDto.getContent(),
                postPostReqDto.getQuantity(),
                postPostReqDto.getDate_buy(),
                postPostReqDto.getDate_expire(),
                postPostReqDto.getIs_opened(),
                postPostReqDto.getAny_zatch()
        );

        postService.join(newPost);


    }


}
