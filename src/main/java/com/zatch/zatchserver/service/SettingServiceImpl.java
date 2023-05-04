package com.zatch.zatchserver.service;

import com.zatch.zatchserver.repository.SettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SettingServiceImpl implements SettingService {

    private final SettingRepository settingRepository;

    @Override
    public Boolean alertAgree(Long userId, Boolean alertAgree) {
        return settingRepository.postAlertAgree(userId, alertAgree);
    }
}
