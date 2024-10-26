package net.accelbyte.extend.serviceextension.vivox;

import lombok.Data;

@Data
public class TokenData {
    public String value;
    public TokenClaims claims;
    TokenData(String value, TokenClaims claims) {
        this.value = value;
        this.claims = claims;
    }
}
