package net.accelbyte.extend.serviceextension.vivox;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({ "vxi", "sub", "f", "iss", "vxa", "t", "exp" })
public class TokenClaims {

    @JsonProperty("vxi")
    private int uniqueId = 1;

    @JsonProperty("sub")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String subject = null;

    @JsonProperty("f")
    private String from = "";

    @JsonProperty("iss")
    private String issuer = "";

    @JsonProperty("vxa")
    private String action = "";

    @JsonProperty("t")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String to = null;

    @JsonProperty("exp")
    private long expiryTime = 0;
}