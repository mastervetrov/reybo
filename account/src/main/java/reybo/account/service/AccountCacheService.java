package reybo.account.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class AccountCacheService {

    @CacheEvict(value = "account", key = "#userId")
    public void evictAccountCache(UUID userId) {
        log.debug("🗑️ Инвалидирован кэш аккаунта для: {}", userId);
    }
}