// Copyright (c) 2024 AccelByte Inc. All Rights Reserved.
// This is licensed software from AccelByte Inc, for limitations
// and restrictions contact your company contract manager.

using System;
using System.Text;
using System.Collections.Generic;
using System.Text.Json;
using System.Linq;
using System.Security.Cryptography;
using System.Threading.Channels;

namespace AccelByte.Extend.Vivox.Authentication.Server.Vivox
{
    public class TokenGenerator : ITokenGenerator
    {
        protected string EncodeBase64Url(string source)
        {
            var bSource = Encoding.UTF8.GetBytes(source);
            var encoded = Convert.ToBase64String(bSource).TrimEnd('=');
            return encoded.Replace("+", "-").Replace("/", "_");
        }

        protected string EncodeBase64Url(byte[] source)
        {
            var encoded = Convert.ToBase64String(source).TrimEnd('=');
            return encoded.Replace("+", "-").Replace("/", "_");
        }

        protected string FormatUserName(IVivoxConfigProvider config, string userId)
        {
            return $"{config.Protocol}:.{config.Issuer}.{userId}.@{config.Domain}";
        }

        protected string FormatChannelName(IVivoxConfigProvider config, string channelId, string channelType)
        {
            return $"{config.Protocol}:{config.ChannelPrefix}-{channelType}-{config.Issuer}.{channelId}@{config.Domain}";
        }

        public int GenerateUniqueId()
        {
            byte[] bGuid = Guid.NewGuid().ToByteArray();
            return bGuid.Aggregate(0, (a, b) => a + b);
        }

        public TokenData Generate(TokenClaims claims, string secret)
        {
            List<string> segments = new List<string>();
            segments.Add(EncodeBase64Url("{}"));

            string payload = JsonSerializer.Serialize(claims);
            segments.Add(EncodeBase64Url(payload));

            string dataToSign = String.Join(".", segments);

            byte[] bSecret = Encoding.ASCII.GetBytes(secret);
            byte[] bData = Encoding.ASCII.GetBytes(dataToSign);
            using var hmac = new HMACSHA256(bSecret);
            byte[] bSignature = hmac.ComputeHash(bData);

            string signature = EncodeBase64Url(bSignature);
            segments.Add(signature);

            return new TokenData(String.Join(".", segments), claims);
        }

        #region Full param
        public TokenData Generate(IVivoxConfigProvider config, int uniqueId, long expiryTime, string userId, string action)
        {
            TokenClaims claims = new TokenClaims()
            {
                UniqueId = uniqueId,
                Issuer = config.Issuer,
                From = FormatUserName(config, userId),
                ExpiryTime = expiryTime,
                Action = action
            };

            return Generate(claims, config.SigningKey);
        }

        public TokenData Generate(IVivoxConfigProvider config, int uniqueId, long expiryTime, string userId, string action, string channelType, string channelId)
        {
            TokenClaims claims = new TokenClaims()
            {
                UniqueId = uniqueId,
                Issuer = config.Issuer,
                From = FormatUserName(config, userId),
                To = FormatChannelName(config, channelId, channelType),
                ExpiryTime = expiryTime,
                Action = action
            };

            return Generate(claims, config.SigningKey);
        }

        public TokenData Generate(IVivoxConfigProvider config, int uniqueId, long expiryTime, string userId, string action, string channelType, string channelId, string targetUserId)
        {
            TokenClaims claims = new TokenClaims()
            {
                UniqueId = uniqueId,
                Issuer = config.Issuer,
                From = FormatUserName(config, userId),
                Subject = FormatUserName(config, targetUserId),
                To = FormatChannelName(config, channelId, channelType),
                ExpiryTime = expiryTime,
                Action = action
            };

            return Generate(claims, config.SigningKey);
        }
        #endregion

        #region Calculated expiry and unique id
        public TokenData Generate(IVivoxConfigProvider config, string userId, string action)
        {
            long currentTs = DateTimeOffset.UtcNow.ToUnixTimeSeconds();
            return Generate(config, GenerateUniqueId(), currentTs + config.DefaultExpiry, userId, action);
        }

        public TokenData Generate(IVivoxConfigProvider config, string userId, string action, string channelType, string channelId)
        {
            long currentTs = DateTimeOffset.UtcNow.ToUnixTimeSeconds();
            return Generate(config, GenerateUniqueId(), currentTs + config.DefaultExpiry, userId, action, channelType, channelId);
        }

        public TokenData Generate(IVivoxConfigProvider config, string userId, string action, string channelType, string channelId, string targetUserId)
        {
            long currentTs = DateTimeOffset.UtcNow.ToUnixTimeSeconds();
            return Generate(config, GenerateUniqueId(), currentTs + config.DefaultExpiry, userId, action, channelType, channelId, targetUserId);            
        }
        #endregion        
    }
}
