package com.zatch.zatchserver.repository;


import com.zatch.zatchserver.domain.Post;

import java.util.List;

public interface PostRepository {

    List<Post> findAllByOrderByCreatedAtDesc();


}


