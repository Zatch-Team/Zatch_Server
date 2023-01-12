package com.zatch.zatchserver.service;

import com.zatch.zatchserver.domain.Post;
import com.zatch.zatchserver.repository.PostRepository;

import java.util.List;

public class PostService {

    private final PostRepository postRepository;


    public List<Post> getPostList() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    public Long join(Post newPost) {
        return null;
    }
}
