package com.zatch.zatchserver.repository;

public interface SettingRepository {
    Boolean postAlertAgree(Long userId, Boolean alertAgree);
}
