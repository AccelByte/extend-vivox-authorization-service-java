// Copyright (c) 2024 AccelByte Inc. All Rights Reserved.
// This is licensed software from AccelByte Inc, for limitations
// and restrictions contact your company contract manager.

using System;
using System.Text.Json.Serialization;

namespace AccelByte.Extend.Vivox.Authentication.Server.Vivox
{
    public class TokenClaims
    {
        [JsonPropertyOrder(1)]
        [JsonPropertyName("vxi")]
        public int UniqueId { get; set; } = 1;

        [JsonPropertyOrder(2)]
        [JsonPropertyName("sub")]
        [JsonIgnore(Condition = JsonIgnoreCondition.WhenWritingNull)]
        public string? Subject { get; set; } = null;

        [JsonPropertyOrder(3)]
        [JsonPropertyName("f")]
        public string From { get; set; } = "";

        [JsonPropertyOrder(4)]
        [JsonPropertyName("iss")]
        public string Issuer { get; set; } = "";

        [JsonPropertyOrder(5)]
        [JsonPropertyName("vxa")]
        public string Action { get; set; } = "";

        [JsonPropertyOrder(6)]
        [JsonPropertyName("t")]
        [JsonIgnore(Condition = JsonIgnoreCondition.WhenWritingNull)]
        public string? To { get; set; } = null;

        [JsonPropertyOrder(7)]
        [JsonPropertyName("exp")]
        public long ExpiryTime { get; set; } = 0;
    }
}
