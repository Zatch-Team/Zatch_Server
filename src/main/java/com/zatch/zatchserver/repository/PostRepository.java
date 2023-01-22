package com.zatch.zatchserver.repository;


import com.zatch.zatchserver.domain.Post;
import java.util.Optional;

public interface PostRepository {

    static int save(Post.PostBuilder post);

    static Optional<Post> findById(int postId);
}


