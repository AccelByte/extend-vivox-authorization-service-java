// Copyright (c) 2024 AccelByte Inc. All Rights Reserved.
// This is licensed software from AccelByte Inc, for limitations
// and restrictions contact your company contract manager.

using System;

namespace AccelByte.Extend.Vivox.Authentication.Server.Vivox
{
    public class VivoxConfigProvider : IVivoxConfigProvider
    {
        public string Issuer { get; set; } = "demo";

        public string Domain { get; set; } = "tla.vivox.com";

        public string SigningKey { get; set; } = "";

        public string ChannelPrefix { get; set; } = "confctl";

        public string Protocol { get; set; } = "sip";

        public int DefaultExpiry { get; set; } = 90;

        public void ReadEnvironmentVariables()
        {
            string? vIssuer = Environment.GetEnvironmentVariable("VIVOX_ISSUER");
            if ((vIssuer != null) && (vIssuer.Trim() != ""))
                Issuer = vIssuer.Trim();

            string? vDomain = Environment.GetEnvironmentVariable("VIVOX_DOMAIN");
            if ((vDomain != null) && (vDomain.Trim() != ""))
                Domain = vDomain.Trim();

            string? vSigningKey = Environment.GetEnvironmentVariable("VIVOX_SIGNING_KEY");
            if ((vSigningKey != null) && (vSigningKey.Trim() != ""))
                SigningKey = vSigningKey.Trim();
        }
    }
}
