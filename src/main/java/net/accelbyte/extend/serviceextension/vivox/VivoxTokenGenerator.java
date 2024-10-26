package net.accelbyte.extend.serviceextension.vivox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

public class VivoxTokenGenerator implements TokenGenerator {

    protected String encodeBase64Url(String source) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(source.getBytes(StandardCharsets.UTF_8));
    }

    protected String encodeBase64Url(byte[] source) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(source);
    }

    protected String formatUserName(ConfigProvider config, String userId) {
        return String.format("%s:.%s.%s.@%s", config.getProtocol(), config.getIssuer(), userId, config.getDomain());
    }

    protected String formatChannelName(ConfigProvider config, String channelId, String channelType) {
        return String.format("%s:%s-%s-%s.%s@%s", config.getProtocol(), config.getChannelPrefix(), channelType, config.getIssuer(), channelId, config.getDomain());
    }

    public int generateUniqueId() {
        UUID uuid = UUID.randomUUID();
        byte[] bGuid = ByteBuffer.wrap(new byte[16])
                .putLong(uuid.getMostSignificantBits())
                .putLong(uuid.getLeastSignificantBits())
                .array();

        return IntStream.range(0, bGuid.length)
                .map(i -> bGuid[i] & 0xFF) // Masking to treat as unsigned byte
                .sum();
    }

    public TokenData generate(TokenClaims claims, String secret) throws JsonProcessingException {
        List<String> segments = new ArrayList<>();
        segments.add(encodeBase64Url("{}"));

        ObjectMapper objectMapper = new ObjectMapper();
        String payload = objectMapper.writeValueAsString(claims);
        segments.add(encodeBase64Url(payload));

        String dataToSign = String.join(".", segments);

        byte[] bSecret = secret.getBytes(StandardCharsets.US_ASCII);
        byte[] bData = dataToSign.getBytes(StandardCharsets.US_ASCII);

        try {
            Mac hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(bSecret, "HmacSHA256");
            hmac.init(secretKeySpec);
            byte[] bSignature = hmac.doFinal(bData);

            String signature = encodeBase64Url(bSignature);
            segments.add(signature);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate HMACSHA256 signature", e);
        }

        return new TokenData(String.join(".", segments), claims);
    }

    public TokenData generate(ConfigProvider config, int uniqueId, long expiryTime, String userId, String action) throws JsonProcessingException {
        TokenClaims claims = new TokenClaims();
        claims.setUniqueId(uniqueId);
        claims.setIssuer(config.getIssuer());
        claims.setFrom(formatUserName(config, userId));
        claims.setExpiryTime(expiryTime);
        claims.setAction(action);

        return generate(claims, config.getSigningKey());
    }

    public TokenData generate(ConfigProvider config, int uniqueId, long expiryTime, String userId, String action, String channelType, String channelId) throws JsonProcessingException {
        TokenClaims claims = new TokenClaims();
        claims.setUniqueId(uniqueId);
        claims.setIssuer(config.getIssuer());
        claims.setFrom(formatUserName(config, userId));
        claims.setTo(formatChannelName(config, channelId, channelType));
        claims.setExpiryTime(expiryTime);
        claims.setAction(action);

        return generate(claims, config.getSigningKey());
    }

    public TokenData generate(ConfigProvider config, int uniqueId, long expiryTime, String userId, String action, String channelType, String channelId, String targetUserId) throws JsonProcessingException {
        TokenClaims claims = new TokenClaims();
        claims.setUniqueId(uniqueId);
        claims.setIssuer(config.getIssuer());
        claims.setFrom(formatUserName(config, userId));
        claims.setSubject(formatUserName(config, targetUserId));
        claims.setTo(formatChannelName(config, channelId, channelType));
        claims.setExpiryTime(expiryTime);
        claims.setAction(action);

        return generate(claims, config.getSigningKey());
    }

    public TokenData generate(ConfigProvider config, String userId, String action) throws JsonProcessingException {
        long currentTs = System.currentTimeMillis() / 1000L;
        return generate(config, generateUniqueId(), currentTs + config.getDefaultExpiry(), userId, action);
    }

    public TokenData generate(ConfigProvider config, String userId, String action, String channelType, String channelId) throws JsonProcessingException {
        long currentTs = System.currentTimeMillis() / 1000L;
        return generate(config, generateUniqueId(), currentTs + config.getDefaultExpiry(), userId, action, channelType, channelId);
    }

    public TokenData generate(ConfigProvider config, String userId, String action, String channelType, String channelId, String targetUserId) throws JsonProcessingException {
        long currentTs = System.currentTimeMillis() / 1000L;
        return generate(config, generateUniqueId(), currentTs + config.getDefaultExpiry(), userId, action, channelType, channelId, targetUserId);
    }
}