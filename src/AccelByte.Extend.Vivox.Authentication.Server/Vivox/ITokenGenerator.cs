// Copyright (c) 2024 AccelByte Inc. All Rights Reserved.
// This is licensed software from AccelByte Inc, for limitations
// and restrictions contact your company contract manager.

using System;

namespace AccelByte.Extend.Vivox.Authentication.Server.Vivox
{
    public interface ITokenGenerator
    {
        int GenerateUniqueId();

        TokenData Generate(TokenClaims claims, string secret);

        TokenData Generate(IVivoxConfigProvider config, int uniqueId, long expiryTime, string userId, string action);

        TokenData Generate(IVivoxConfigProvider config, int uniqueId, long expiryTime, string userId, string action, string channelType, string channelId);

        TokenData Generate(IVivoxConfigProvider config, int uniqueId, long expiryTime, string userId, string action, string channelType, string channelId, string targetUserId);

        TokenData Generate(IVivoxConfigProvider config, string userId, string action);

        TokenData Generate(IVivoxConfigProvider config, string userId, string action, string channelType, string channelId);

        TokenData Generate(IVivoxConfigProvider config, string userId, string action, string channelType, string channelId, string targetUserId);
    }
}
