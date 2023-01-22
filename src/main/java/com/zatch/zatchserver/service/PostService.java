package com.zatch.zatchserver.service;

import com.zatch.zatchserver.ErrorHandler.ApiResponse;
import com.zatch.zatchserver.domain.Post;
import com.zatch.zatchserver.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Service Create, Update, Delete 의 로직 처리
@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public static Post write(Post.PostBuilder post) {
        int status = PostRepository.save(post);
        return PostRepository.findById(status).get();
    }

    public ApiResponse findByIdWithLike(Post postId) {
        ApiResponse.ResponseMap result = new ApiResponse.ResponseMap();
        Optional<Post> Op_post = PostRepository.findById(postId);
        if(Op_post.isPresent()) {
            Post post = Op_post.get();
            result.setResult(post);
        }
        return result;
    }
}
