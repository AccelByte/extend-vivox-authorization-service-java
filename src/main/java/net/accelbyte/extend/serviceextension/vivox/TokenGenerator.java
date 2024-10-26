package net.accelbyte.extend.serviceextension.vivox;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface TokenGenerator {
    int generateUniqueId();

    TokenData generate(TokenClaims claims, String secret) throws JsonProcessingException;

    TokenData generate(ConfigProvider config, int uniqueId, long expiryTime, String userId, String action) throws JsonProcessingException;

    TokenData generate(ConfigProvider config, int uniqueId, long expiryTime, String userId, String action, String channelType, String channelId) throws JsonProcessingException;

    TokenData generate(ConfigProvider config, int uniqueId, long expiryTime, String userId, String action, String channelType, String channelId, String targetUserId) throws JsonProcessingException;

    TokenData generate(ConfigProvider config, String userId, String action) throws JsonProcessingException;

    TokenData generate(ConfigProvider config, String userId, String action, String channelType, String channelId) throws JsonProcessingException;

    TokenData generate(ConfigProvider config, String userId, String action, String channelType, String channelId, String targetUserId) throws JsonProcessingException;



}