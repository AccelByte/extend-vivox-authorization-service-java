// Copyright (c) 2024 AccelByte Inc. All Rights Reserved.
// This is licensed software from AccelByte Inc, for limitations
// and restrictions contact your company contract manager.

using System;

namespace AccelByte.Extend.Vivox.Authentication.Server.Vivox
{
    public class TokenData
    {
        public string Value { get; }

        public TokenClaims Claims { get; }

        internal TokenData(string value, TokenClaims claims)
        {
            Value = value;
            Claims = claims;
        }
    }
}
