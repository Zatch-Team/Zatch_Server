package com.zatch.zatchserver.repository;


import com.zatch.zatchserver.domain.Zatch;

import java.util.List;

public interface ZatchRepository {

    List<Zatch> findAllByOrderByCreatedAtDesc();

}


