package com.example.resume.config;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Token 黑名单（用于演示注销）/ Token blacklist (for logout demo)
 * 说明：
 * - 真实生产建议用 Redis 或短期 access token + refresh token
 * - This is an in-memory demo; production should use Redis or refresh-token design
 */
@Service
public class TokenBlacklistService {

    private final Set<String> blacklisted = ConcurrentHashMap.newKeySet();

    public void blacklist(String token) {
        blacklisted.add(token);
    }

    public boolean isBlacklisted(String token) {
        return blacklisted.contains(token);
    }
}
