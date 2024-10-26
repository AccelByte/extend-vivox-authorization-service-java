package net.accelbyte.extend.serviceextension.vivox;

public interface ConfigProvider {
    String getIssuer();
    String getDomain();
    String getSigningKey();
    String getChannelPrefix();
    String getProtocol();
    int getDefaultExpiry();
}
