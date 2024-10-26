package net.accelbyte.extend.serviceextension.vivox;

import lombok.Data;

@Data
public class VivoxConfigProvider implements ConfigProvider {
    public String issuer = "demo";;
    public String domain = "tla.vivox.com";
    public String signingKey = "";
    public String channelPrefix = "confctl";
    public String protocol = "sip";;
    public int defaultExpiry = 90;

    public void readEnvironmentVariables()
    {
        String vIssuer = System.getenv("VIVOX_ISSUER");
        if ((vIssuer != null) && (vIssuer.trim() != ""))
            issuer = vIssuer.trim();

        String vDomain = System.getenv("VIVOX_DOMAIN");
        if ((vDomain != null) && (vDomain.trim() != ""))
            domain = vDomain.trim();

        String vSigningKey = System.getenv("VIVOX_SIGNING_KEY");
        if ((vSigningKey != null) && (vSigningKey.trim() != ""))
            signingKey = vSigningKey.trim();
    }
}
