package net.accelbyte.extend.serviceextension.config;

import net.accelbyte.extend.serviceextension.vivox.ConfigProvider;
import net.accelbyte.extend.serviceextension.vivox.TokenGenerator;
import net.accelbyte.extend.serviceextension.vivox.VivoxConfigProvider;
import net.accelbyte.extend.serviceextension.vivox.VivoxTokenGenerator;
import net.accelbyte.sdk.core.AccelByteSDK;
import net.accelbyte.sdk.core.client.OkhttpClient;
import net.accelbyte.sdk.core.repository.DefaultConfigRepository;
import net.accelbyte.sdk.core.repository.DefaultTokenRefreshRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerErrorException;

@Configuration
public class AppConfig {
    @Bean
    public ConfigProvider provideConfigProvider() {
        VivoxConfigProvider vivoxConfigProvider = new VivoxConfigProvider();
        vivoxConfigProvider.readEnvironmentVariables();
        return vivoxConfigProvider;
    }

    @Bean
    public TokenGenerator provideTokenGenerator() {
        return new VivoxTokenGenerator();
    }

    @Bean
    public AccelByteSDK provideAccelbyteSdk() {
        AccelByteSDK sdk = new AccelByteSDK(
                new OkhttpClient(), new DefaultTokenRefreshRepository(), new DefaultConfigRepository());
        boolean isSuccess = sdk.loginClient();
        if (!isSuccess) {
            throw new ServerErrorException("failed to sdk.loginClient()", new IllegalArgumentException());
        }
        return sdk;
    }
}
