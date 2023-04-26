package com.zatch.zatchserver.service;

import com.zatch.zatchserver.repository.BlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlockServiceImpl implements BlockService {

    private final BlockRepository blockRepository;

    @Override
    public String blockUser(Long userId, Long blockedId) {
        return blockRepository.postBlock(userId, blockedId);
    }
}
