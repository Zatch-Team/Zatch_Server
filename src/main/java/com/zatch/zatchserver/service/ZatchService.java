package com.zatch.zatchserver.service;

import com.zatch.zatchserver.domain.Zatch;
import com.zatch.zatchserver.dto.NewZatchParam;
import com.zatch.zatchserver.repository.ZatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ZatchService {
    private final ZatchRepository zatchRepository;

    @Transactional
    public void register(NewZatchParam newZatchParam) {
        Zatch newZatch = Zatch.builder()
                .userId(newZatchParam.getUserId())
                .userId(newZatchParam.getUserId())
                .categoryId(newZatchParam.getCategoryId())
                .isFree(newZatchParam.getIsFree())
                .itemName(newZatchParam.getItemName())
                .content(newZatchParam.getContent())
                .quantity(newZatchParam.getQuantity())
                .purchaseDate(newZatchParam.getPurchaseDate())
                .expirationDate(newZatchParam.getExpirationDate())
                .isOpened(newZatchParam.getIsOpened())
                .allowAnyZatch(newZatchParam.getAllowAnyZatch())
                .build();
        zatchRepository.save(newZatch);
    }

    @Transactional
    public Integer makeZatchLike(Long userId, Long zatchId) {
        return zatchRepository.increaseLike(userId, zatchId);
    }
}
