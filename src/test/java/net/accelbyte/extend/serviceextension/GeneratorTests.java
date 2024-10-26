package net.accelbyte.extend.serviceextension;


import net.accelbyte.extend.serviceextension.vivox.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GeneratorTests {

    @Autowired
    private ConfigProvider config;

    @Autowired
    private TokenGenerator generator;

    @BeforeEach
    public void setUp() {
        VivoxConfigProvider vivoxConfig = new VivoxConfigProvider();
        vivoxConfig.setIssuer("blindmelon-AppName-dev");
        vivoxConfig.setDomain("tla.vivox.com");
        vivoxConfig.setSigningKey("secret!");
        config = vivoxConfig;
    }

    @Test
    public void loginTest() throws Exception {
        String expectedToken = "e30.eyJ2eGkiOjkzMzAwMCwiZiI6InNpcDouYmxpbmRtZWxvbi1BcHBOYW1lLWRldi5qZXJreS5AdGxhLnZpdm94LmNvbSIsImlzcyI6ImJsaW5kbWVsb24tQXBwTmFtZS1kZXYiLCJ2eGEiOiJsb2dpbiIsImV4cCI6MTYwMDM0OTQwMH0.YJwjX0P2Pjk1dzFpIo1fjJM21pphfBwHm8vShJib8ds";
        TokenData actualToken = generator.generate(config, 933000, 1600349400, "jerky", "login");
        assertEquals(expectedToken, actualToken.getValue());
    }

    @Test
    public void joinTest() throws Exception {
        String expectedToken = "e30.eyJ2eGkiOjQ0NDAwMCwiZiI6InNpcDouYmxpbmRtZWxvbi1BcHBOYW1lLWRldi5qZXJreS5AdGxhLnZpdm94LmNvbSIsImlzcyI6ImJsaW5kbWVsb24tQXBwTmFtZS1kZXYiLCJ2eGEiOiJqb2luIiwidCI6InNpcDpjb25mY3RsLWctYmxpbmRtZWxvbi1BcHBOYW1lLWRldi50ZXN0Y2hhbm5lbEB0bGEudml2b3guY29tIiwiZXhwIjoxNjAwMzQ5NDAwfQ.u7us5eCxOBtuEZuDg1HapEEgxLedLaliIy7gOMfbeko";
        TokenData actualToken = generator.generate(config, 444000, 1600349400, "jerky", "join", "g", "testchannel");
        assertEquals(expectedToken, actualToken.getValue());
    }

    @Test
    public void joinMutedTest() throws Exception {
        String expectedToken = "e30.eyJ2eGkiOjU0MjY4MCwiZiI6InNpcDouYmxpbmRtZWxvbi1BcHBOYW1lLWRldi5qZXJreS5AdGxhLnZpdm94LmNvbSIsImlzcyI6ImJsaW5kbWVsb24tQXBwTmFtZS1kZXYiLCJ2eGEiOiJqb2luX211dGVkIiwidCI6InNpcDpjb25mY3RsLWctYmxpbmRtZWxvbi1BcHBOYW1lLWRldi50ZXN0Y2hhbm5lbEB0bGEudml2b3guY29tIiwiZXhwIjoxNjAwMzQ5NDAwfQ.N6sZL3F3e-p2KLQlMweXnbGNzE7Qc91rn_uqCEtRjsc";
        TokenData actualToken = generator.generate(config, 542680, 1600349400, "jerky", "join_muted", "g", "testchannel");
        assertEquals(expectedToken, actualToken.getValue());
    }

    @Test
    public void userKickUserTest() throws Exception {
        String expectedToken = "e30.eyJ2eGkiOjY2NTAwMCwic3ViIjoic2lwOi5ibGluZG1lbG9uLUFwcE5hbWUtZGV2Lmplcmt5LkB0bGEudml2b3guY29tIiwiZiI6InNpcDouYmxpbmRtZWxvbi1BcHBOYW1lLWRldi5iZWVmLkB0bGEudml2b3guY29tIiwiaXNzIjoiYmxpbmRtZWxvbi1BcHBOYW1lLWRldiIsInZ4YSI6ImtpY2siLCJ0Ijoic2lwOmNvbmZjdGwtZy1ibGluZG1lbG9uLUFwcE5hbWUtZGV2LnRlc3RjaGFubmVsQHRsYS52aXZveC5jb20iLCJleHAiOjE2MDAzNDk0MDB9.kKnWD3smth6KUuRaY11O-yqAbXy2L2wDZeIoDK_098c";
        TokenData actualToken = generator.generate(config, 665000, 1600349400, "beef", "kick", "g", "testchannel", "jerky");
        assertEquals(expectedToken, actualToken.getValue());
    }

    @Test
    public void adminKickUserFromChannelTest() throws Exception {
        String expectedToken = "e30.eyJ2eGkiOjgwMDAsInN1YiI6InNpcDouYmxpbmRtZWxvbi1BcHBOYW1lLWRldi5qZXJreS5AdGxhLnZpdm94LmNvbSIsImYiOiJzaXA6YmxpbmRtZWxvbi1BcHBOYW1lLWRldi1BZG1pbkB0bGEudml2b3guY29tIiwiaXNzIjoiYmxpbmRtZWxvbi1BcHBOYW1lLWRldiIsInZ4YSI6ImtpY2siLCJ0Ijoic2lwOmNvbmZjdGwtZy1ibGluZG1lbG9uLUFwcE5hbWUtZGV2LnRlc3RjaGFubmVsQHRsYS52aXZveC5jb20iLCJleHAiOjE2MDAzNDk0MDB9.7Fn08cctqltxNxPAAeOhPQd4KCsmT1ue1EDIxUNQ3gg";

        TokenClaims claims = new TokenClaims();
        claims.setUniqueId(8000);
        claims.setSubject("sip:.blindmelon-AppName-dev.jerky.@tla.vivox.com");
        claims.setFrom("sip:blindmelon-AppName-dev-Admin@tla.vivox.com");
        claims.setIssuer("blindmelon-AppName-dev");
        claims.setAction("kick");
        claims.setTo("sip:confctl-g-blindmelon-AppName-dev.testchannel@tla.vivox.com");
        claims.setExpiryTime(1600349400);

        TokenData actualToken = generator.generate(claims, "secret!");
        assertEquals(expectedToken, actualToken.getValue());
    }

    @Test
    public void adminKickUserFromServerTest() throws Exception {
        String expectedToken = "e30.eyJ2eGkiOjYxMzY0Miwic3ViIjoic2lwOi5ibGluZG1lbG9uLUFwcE5hbWUtZGV2Lmplcmt5LkB0bGEudml2b3guY29tIiwiZiI6InNpcDpibGluZG1lbG9uLUFwcE5hbWUtZGV2LUFkbWluQHRsYS52aXZveC5jb20iLCJpc3MiOiJibGluZG1lbG9uLUFwcE5hbWUtZGV2IiwidnhhIjoia2ljayIsInQiOiJzaXA6YmxpbmRtZWxvbi1BcHBOYW1lLWRldi1zZXJ2aWNlQHRsYS52aXZveC5jb20iLCJleHAiOjE2MDAzNDk0MDB9.jinc73lQ_ZSN4Mb8WLFK7Clu-Se9LG-QifXKfpaa3g4";

        TokenClaims claims = new TokenClaims();
        claims.setUniqueId(613642);
        claims.setSubject("sip:.blindmelon-AppName-dev.jerky.@tla.vivox.com");
        claims.setFrom("sip:blindmelon-AppName-dev-Admin@tla.vivox.com");
        claims.setIssuer("blindmelon-AppName-dev");
        claims.setAction("kick");
        claims.setTo("sip:blindmelon-AppName-dev-service@tla.vivox.com");
        claims.setExpiryTime(1600349400);

        TokenData actualToken = generator.generate(claims, "secret!");
        assertEquals(expectedToken, actualToken.getValue());
    }

    @Test
    public void adminDropAllTest() throws Exception {
        String expectedToken = "e30.eyJ2eGkiOjcyOTYxNCwiZiI6InNpcDpibGluZG1lbG9uLUFwcE5hbWUtZGV2LUFkbWluQHRsYS52aXZveC5jb20iLCJpc3MiOiJibGluZG1lbG9uLUFwcE5hbWUtZGV2IiwidnhhIjoia2ljayIsInQiOiJzaXA6Y29uZmN0bC1nLWJsaW5kbWVsb24tQXBwTmFtZS1kZXYudGVzdGNoYW5uZWxAdGxhLnZpdm94LmNvbSIsImV4cCI6MTYwMDM0OTQwMH0.bvNDTcXIHRpckAiFzy3wPiwgYGks4E9_WuEA5HMGpGE";

        TokenClaims claims = new TokenClaims();
        claims.setUniqueId(729614);
        claims.setFrom("sip:blindmelon-AppName-dev-Admin@tla.vivox.com");
        claims.setIssuer("blindmelon-AppName-dev");
        claims.setAction("kick");
        claims.setTo("sip:confctl-g-blindmelon-AppName-dev.testchannel@tla.vivox.com");
        claims.setExpiryTime(1600349400);

        TokenData actualToken = generator.generate(claims, "secret!");
        assertEquals(expectedToken, actualToken.getValue());
    }

    @Test
    public void userMuteTest() throws Exception {
        String expectedToken = "e30.eyJ2eGkiOjEyMzQ1Niwic3ViIjoic2lwOi5ibGluZG1lbG9uLUFwcE5hbWUtZGV2Lmplcmt5LkB0bGEudml2b3guY29tIiwiZiI6InNpcDouYmxpbmRtZWxvbi1BcHBOYW1lLWRldi5iZWVmLkB0bGEudml2b3guY29tIiwiaXNzIjoiYmxpbmRtZWxvbi1BcHBOYW1lLWRldiIsInZ4YSI6Im11dGUiLCJ0Ijoic2lwOmNvbmZjdGwtZy1ibGluZG1lbG9uLUFwcE5hbWUtZGV2LnRlc3RjaGFubmVsQHRsYS52aXZveC5jb20iLCJleHAiOjE2MDAzNDk0MDB9.vM9zkCXTORjgv8w7eiMHHHkc4DumTwR_-I06y4SnpHA";
        TokenData actualToken = generator.generate(config, 123456, 1600349400, "beef", "mute", "g", "testchannel", "jerky");
        assertEquals(expectedToken, actualToken.getValue());
    }

    @Test
    public void adminMuteTest() throws Exception {
        String expectedToken = "e30.eyJ2eGkiOjY1NDMyMSwic3ViIjoic2lwOi5ibGluZG1lbG9uLUFwcE5hbWUtZGV2Lmplcmt5LkB0bGEudml2b3guY29tIiwiZiI6InNpcDpibGluZG1lbG9uLUFwcE5hbWUtZGV2LUFkbWluQHRsYS52aXZveC5jb20iLCJpc3MiOiJibGluZG1lbG9uLUFwcE5hbWUtZGV2IiwidnhhIjoibXV0ZSIsInQiOiJzaXA6Y29uZmN0bC1nLWJsaW5kbWVsb24tQXBwTmFtZS1kZXYudGVzdGNoYW5uZWxAdGxhLnZpdm94LmNvbSIsImV4cCI6MTYwMDM0OTQwMH0.ix0mFGS1XDXCBXH044f6B2JxutExbH2hZjGqZAwoHH8";

        TokenClaims claims = new TokenClaims();
        claims.setUniqueId(654321);
        claims.setSubject("sip:.blindmelon-AppName-dev.jerky.@tla.vivox.com");
        claims.setFrom("sip:blindmelon-AppName-dev-Admin@tla.vivox.com");
        claims.setIssuer("blindmelon-AppName-dev");
        claims.setAction("mute");
        claims.setTo("sip:confctl-g-blindmelon-AppName-dev.testchannel@tla.vivox.com");
        claims.setExpiryTime(1600349400);

        TokenData actualToken = generator.generate(claims, "secret!");
        assertEquals(expectedToken, actualToken.getValue());
    }

    @Test
    public void userMuteAllTest() throws Exception {
        String expectedToken = "e30.eyJ2eGkiOjE5MjgzLCJmIjoic2lwOi5ibGluZG1lbG9uLUFwcE5hbWUtZGV2LmJlZWYuQHRsYS52aXZveC5jb20iLCJpc3MiOiJibGluZG1lbG9uLUFwcE5hbWUtZGV2IiwidnhhIjoibXV0ZSIsInQiOiJzaXA6Y29uZmN0bC1nLWJsaW5kbWVsb24tQXBwTmFtZS1kZXYudGVzdGNoYW5uZWxAdGxhLnZpdm94LmNvbSIsImV4cCI6MTYwMDM0OTQwMH0.fARLW2eX10ZbiIl_5WIg4bhPbYIhn2xfCcUNySfwBMs";
        TokenData actualToken = generator.generate(config, 19283, 1600349400, "beef", "mute", "g", "testchannel");
        assertEquals(expectedToken, actualToken.getValue());
    }

    @Test
    public void adminMuteAllTest() throws Exception {
        String expectedToken = "e30.eyJ2eGkiOjgyNTY0NywiZiI6InNpcDpibGluZG1lbG9uLUFwcE5hbWUtZGV2LUFkbWluQHRsYS52aXZveC5jb20iLCJpc3MiOiJibGluZG1lbG9uLUFwcE5hbWUtZGV2IiwidnhhIjoibXV0ZSIsInQiOiJzaXA6Y29uZmN0bC1nLWJsaW5kbWVsb24tQXBwTmFtZS1kZXYudGVzdGNoYW5uZWxAdGxhLnZpdm94LmNvbSIsImV4cCI6MTYwMDM0OTQwMH0.zpyvlBbVAKatuCeELb0Q1PsCb4tg0yacneL_sYHIVaw";

        TokenClaims claims = new TokenClaims();
        claims.setUniqueId(825647);
        claims.setFrom("sip:blindmelon-AppName-dev-Admin@tla.vivox.com");
        claims.setIssuer("blindmelon-AppName-dev");
        claims.setAction("mute");
        claims.setTo("sip:confctl-g-blindmelon-AppName-dev.testchannel@tla.vivox.com");
        claims.setExpiryTime(1600349400);

        TokenData actualToken = generator.generate(claims, "secret!");
        assertEquals(expectedToken, actualToken.getValue());
    }

    @Test
    public void transcriptionTest() throws Exception {
        String expectedToken = "e30.eyJ2eGkiOjU0MjY4MCwiZiI6InNpcDouYmxpbmRtZWxvbi1BcHBOYW1lLWRldi5iZWVmLkB0bGEudml2b3guY29tIiwiaXNzIjoiYmxpbmRtZWxvbi1BcHBOYW1lLWRldiIsInZ4YSI6InRyeG4iLCJ0Ijoic2lwOmNvbmZjdGwtZy1ibGluZG1lbG9uLUFwcE5hbWUtZGV2LnRlc3RjaGFubmVsQHRsYS52aXZveC5jb20iLCJleHAiOjE2MDAzNDk0MDB9.-A0w_fcPCZaG5NMksnbSrGSVXNNt25YqlRjcKcLkGnA";
        TokenData actualToken = generator.generate(config, 542680, 1600349400, "beef", "trxn", "g", "testchannel");
        assertEquals(expectedToken, actualToken.getValue());
    }
}