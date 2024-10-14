// Copyright (c) 2024 AccelByte Inc. All Rights Reserved.
// This is licensed software from AccelByte Inc, for limitations
// and restrictions contact your company contract manager.

using System;

namespace AccelByte.Extend.Vivox.Authentication.Server.Vivox
{
    public interface IVivoxConfigProvider
    {
        string Issuer { get; }

        string Domain { get; }

        string SigningKey { get; }

        string ChannelPrefix { get; }

        string Protocol { get; }

        int DefaultExpiry { get; }
    }
}
