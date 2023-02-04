package com.zatch.zatchserver.repository;


import com.zatch.zatchserver.domain.Zatch;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ZatchRepository {

    List<Zatch> findAllByOrderByCreatedAtDesc();


    Long register(Zatch zatch);

}


