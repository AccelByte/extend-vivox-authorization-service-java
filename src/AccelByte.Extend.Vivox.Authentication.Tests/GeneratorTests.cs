// Copyright (c) 2024 AccelByte Inc. All Rights Reserved.
// This is licensed software from AccelByte Inc, for limitations
// and restrictions contact your company contract manager.

using System;
using AccelByte.Extend.Vivox.Authentication.Server.Vivox;
using NUnit.Framework;

namespace AccelByte.Extend.Vivox.Authentication.Tests
{
    public class GeneratorTests
    {
        private IVivoxConfigProvider _Config;

        private ITokenGenerator _Generator;

        public GeneratorTests()
        {
            _Config = new VivoxConfigProvider()
            {
                Issuer = "blindmelon-AppName-dev",
                Domain = "tla.vivox.com",
                SigningKey = "secret!"
            };

            _Generator = new TokenGenerator();
        }

        /// <summary>
        /// Test values taken from:
        /// https://docs.vivox.com/v5/general/unity/15_1_160000/en-us/access-token-guide/access-token-examples/example-login-token.htm
        /// </summary>
        [Test]
        public void LoginTest()
        {
            string expectedToken = "e30.eyJ2eGkiOjkzMzAwMCwiZiI6InNpcDouYmxpbmRtZWxvbi1BcHBOYW1lLWRldi5qZXJreS5AdGxhLnZpdm94LmNvbSIsImlzcyI6ImJsaW5kbWVsb24tQXBwTmFtZS1kZXYiLCJ2eGEiOiJsb2dpbiIsImV4cCI6MTYwMDM0OTQwMH0.YJwjX0P2Pjk1dzFpIo1fjJM21pphfBwHm8vShJib8ds";
            var actualToken = _Generator.Generate(_Config, 933000, 1600349400, "jerky", "login");
            Assert.AreEqual(expectedToken, actualToken.Value);
        }

        /// <summary>
        /// Test values taken from:
        /// https://docs.vivox.com/v5/general/unity/15_1_160000/en-us/access-token-guide/access-token-examples/example-join-token.htm
        /// </summary>
        [Test]
        public void JoinTest()
        {
            string expectedToken = "e30.eyJ2eGkiOjQ0NDAwMCwiZiI6InNpcDouYmxpbmRtZWxvbi1BcHBOYW1lLWRldi5qZXJreS5AdGxhLnZpdm94LmNvbSIsImlzcyI6ImJsaW5kbWVsb24tQXBwTmFtZS1kZXYiLCJ2eGEiOiJqb2luIiwidCI6InNpcDpjb25mY3RsLWctYmxpbmRtZWxvbi1BcHBOYW1lLWRldi50ZXN0Y2hhbm5lbEB0bGEudml2b3guY29tIiwiZXhwIjoxNjAwMzQ5NDAwfQ.u7us5eCxOBtuEZuDg1HapEEgxLedLaliIy7gOMfbeko";
            var actualToken = _Generator.Generate(_Config, 444000, 1600349400, "jerky", "join", "g", "testchannel");
            Assert.AreEqual(expectedToken, actualToken.Value);
        }

        /// <summary>
        /// Test values taken from:
        /// https://docs.vivox.com/v5/general/unity/15_1_160000/en-us/access-token-guide/access-token-examples/example-join-muted-token.htm
        /// </summary>
        [Test]
        public void JoinMutedTest()
        {
            string expectedToken = "e30.eyJ2eGkiOjU0MjY4MCwiZiI6InNpcDouYmxpbmRtZWxvbi1BcHBOYW1lLWRldi5qZXJreS5AdGxhLnZpdm94LmNvbSIsImlzcyI6ImJsaW5kbWVsb24tQXBwTmFtZS1kZXYiLCJ2eGEiOiJqb2luX211dGVkIiwidCI6InNpcDpjb25mY3RsLWctYmxpbmRtZWxvbi1BcHBOYW1lLWRldi50ZXN0Y2hhbm5lbEB0bGEudml2b3guY29tIiwiZXhwIjoxNjAwMzQ5NDAwfQ.N6sZL3F3e-p2KLQlMweXnbGNzE7Qc91rn_uqCEtRjsc";
            var actualToken = _Generator.Generate(_Config, 542680, 1600349400, "jerky", "join_muted", "g", "testchannel");
            Assert.AreEqual(expectedToken, actualToken.Value);
        }

        /// <summary>
        /// Test values taken from:
        /// https://docs.vivox.com/v5/general/unity/15_1_160000/en-us/access-token-guide/access-token-examples/example-kick-token.htm
        /// </summary>
        [Test]
        public void UserKickUserTest()
        {
            string expectedToken = "e30.eyJ2eGkiOjY2NTAwMCwic3ViIjoic2lwOi5ibGluZG1lbG9uLUFwcE5hbWUtZGV2Lmplcmt5LkB0bGEudml2b3guY29tIiwiZiI6InNpcDouYmxpbmRtZWxvbi1BcHBOYW1lLWRldi5iZWVmLkB0bGEudml2b3guY29tIiwiaXNzIjoiYmxpbmRtZWxvbi1BcHBOYW1lLWRldiIsInZ4YSI6ImtpY2siLCJ0Ijoic2lwOmNvbmZjdGwtZy1ibGluZG1lbG9uLUFwcE5hbWUtZGV2LnRlc3RjaGFubmVsQHRsYS52aXZveC5jb20iLCJleHAiOjE2MDAzNDk0MDB9.kKnWD3smth6KUuRaY11O-yqAbXy2L2wDZeIoDK_098c";
            var actualToken = _Generator.Generate(_Config, 665000, 1600349400, "beef", "kick", "g", "testchannel", "jerky");
            Assert.AreEqual(expectedToken, actualToken.Value);
        }

        /// <summary>
        /// Test values taken from:
        /// https://docs.vivox.com/v5/general/unity/15_1_160000/en-us/access-token-guide/access-token-examples/example-kick-token.htm
        /// </summary>
        [Test]
        public void AdminKickUserFromChannelTest()
        {
            string expectedToken = "e30.eyJ2eGkiOjgwMDAsInN1YiI6InNpcDouYmxpbmRtZWxvbi1BcHBOYW1lLWRldi5qZXJreS5AdGxhLnZpdm94LmNvbSIsImYiOiJzaXA6YmxpbmRtZWxvbi1BcHBOYW1lLWRldi1BZG1pbkB0bGEudml2b3guY29tIiwiaXNzIjoiYmxpbmRtZWxvbi1BcHBOYW1lLWRldiIsInZ4YSI6ImtpY2siLCJ0Ijoic2lwOmNvbmZjdGwtZy1ibGluZG1lbG9uLUFwcE5hbWUtZGV2LnRlc3RjaGFubmVsQHRsYS52aXZveC5jb20iLCJleHAiOjE2MDAzNDk0MDB9.7Fn08cctqltxNxPAAeOhPQd4KCsmT1ue1EDIxUNQ3gg";

            TokenClaims claims = new TokenClaims()
            {
                UniqueId = 8000,
                Subject = "sip:.blindmelon-AppName-dev.jerky.@tla.vivox.com",
                From = "sip:blindmelon-AppName-dev-Admin@tla.vivox.com",
                Issuer = "blindmelon-AppName-dev",
                Action = "kick",
                To = "sip:confctl-g-blindmelon-AppName-dev.testchannel@tla.vivox.com",
                ExpiryTime = 1600349400
            };

            TokenGenerator generator = new TokenGenerator();
            var actualToken = generator.Generate(claims, "secret!");

            Assert.AreEqual(expectedToken, actualToken.Value);
        }

        /// <summary>
        /// Test values taken from:
        /// https://docs.vivox.com/v5/general/unity/15_1_160000/en-us/access-token-guide/access-token-examples/example-kick-token.htm
        /// </summary>
        [Test]
        public void AdminKickUserFromServerTest()
        {
            string expectedToken = "e30.eyJ2eGkiOjYxMzY0Miwic3ViIjoic2lwOi5ibGluZG1lbG9uLUFwcE5hbWUtZGV2Lmplcmt5LkB0bGEudml2b3guY29tIiwiZiI6InNpcDpibGluZG1lbG9uLUFwcE5hbWUtZGV2LUFkbWluQHRsYS52aXZveC5jb20iLCJpc3MiOiJibGluZG1lbG9uLUFwcE5hbWUtZGV2IiwidnhhIjoia2ljayIsInQiOiJzaXA6YmxpbmRtZWxvbi1BcHBOYW1lLWRldi1zZXJ2aWNlQHRsYS52aXZveC5jb20iLCJleHAiOjE2MDAzNDk0MDB9.jinc73lQ_ZSN4Mb8WLFK7Clu-Se9LG-QifXKfpaa3g4";

            TokenClaims claims = new TokenClaims()
            {
                UniqueId = 613642,
                Subject = "sip:.blindmelon-AppName-dev.jerky.@tla.vivox.com",
                From = "sip:blindmelon-AppName-dev-Admin@tla.vivox.com",
                Issuer = "blindmelon-AppName-dev",
                Action = "kick",
                To = "sip:blindmelon-AppName-dev-service@tla.vivox.com",
                ExpiryTime = 1600349400
            };

            TokenGenerator generator = new TokenGenerator();
            var actualToken = generator.Generate(claims, "secret!");

            Assert.AreEqual(expectedToken, actualToken.Value);
        }

        /// <summary>
        /// Test values taken from:
        /// https://docs.vivox.com/v5/general/unity/15_1_160000/en-us/access-token-guide/access-token-examples/example-drop-all-token.htm
        /// </summary>
        [Test]
        public void AdminDropAllTest()
        {
            string expectedToken = "e30.eyJ2eGkiOjcyOTYxNCwiZiI6InNpcDpibGluZG1lbG9uLUFwcE5hbWUtZGV2LUFkbWluQHRsYS52aXZveC5jb20iLCJpc3MiOiJibGluZG1lbG9uLUFwcE5hbWUtZGV2IiwidnhhIjoia2ljayIsInQiOiJzaXA6Y29uZmN0bC1nLWJsaW5kbWVsb24tQXBwTmFtZS1kZXYudGVzdGNoYW5uZWxAdGxhLnZpdm94LmNvbSIsImV4cCI6MTYwMDM0OTQwMH0.bvNDTcXIHRpckAiFzy3wPiwgYGks4E9_WuEA5HMGpGE";

            TokenClaims claims = new TokenClaims()
            {
                UniqueId = 729614,
                From = "sip:blindmelon-AppName-dev-Admin@tla.vivox.com",
                Issuer = "blindmelon-AppName-dev",
                Action = "kick",
                To = "sip:confctl-g-blindmelon-AppName-dev.testchannel@tla.vivox.com",
                ExpiryTime = 1600349400
            };

            TokenGenerator generator = new TokenGenerator();
            var actualToken = generator.Generate(claims, "secret!");

            Assert.AreEqual(expectedToken, actualToken.Value);
        }

        /// <summary>
        /// Test values taken from:
        /// https://docs.vivox.com/v5/general/unity/15_1_160000/en-us/access-token-guide/access-token-examples/example-mute-token.htm
        /// </summary>
        [Test]
        public void UserMuteTest()
        {
            string expectedToken = "e30.eyJ2eGkiOjEyMzQ1Niwic3ViIjoic2lwOi5ibGluZG1lbG9uLUFwcE5hbWUtZGV2Lmplcmt5LkB0bGEudml2b3guY29tIiwiZiI6InNpcDouYmxpbmRtZWxvbi1BcHBOYW1lLWRldi5iZWVmLkB0bGEudml2b3guY29tIiwiaXNzIjoiYmxpbmRtZWxvbi1BcHBOYW1lLWRldiIsInZ4YSI6Im11dGUiLCJ0Ijoic2lwOmNvbmZjdGwtZy1ibGluZG1lbG9uLUFwcE5hbWUtZGV2LnRlc3RjaGFubmVsQHRsYS52aXZveC5jb20iLCJleHAiOjE2MDAzNDk0MDB9.vM9zkCXTORjgv8w7eiMHHHkc4DumTwR_-I06y4SnpHA";
            var actualToken = _Generator.Generate(_Config, 123456, 1600349400, "beef", "mute", "g", "testchannel", "jerky");
            Assert.AreEqual(expectedToken, actualToken.Value);
        }

        /// <summary>
        /// Test values taken from:
        /// https://docs.vivox.com/v5/general/unity/15_1_160000/en-us/access-token-guide/access-token-examples/example-mute-token.htm
        /// </summary>
        [Test]
        public void AdminMuteTest()
        {
            string expectedToken = "e30.eyJ2eGkiOjY1NDMyMSwic3ViIjoic2lwOi5ibGluZG1lbG9uLUFwcE5hbWUtZGV2Lmplcmt5LkB0bGEudml2b3guY29tIiwiZiI6InNpcDpibGluZG1lbG9uLUFwcE5hbWUtZGV2LUFkbWluQHRsYS52aXZveC5jb20iLCJpc3MiOiJibGluZG1lbG9uLUFwcE5hbWUtZGV2IiwidnhhIjoibXV0ZSIsInQiOiJzaXA6Y29uZmN0bC1nLWJsaW5kbWVsb24tQXBwTmFtZS1kZXYudGVzdGNoYW5uZWxAdGxhLnZpdm94LmNvbSIsImV4cCI6MTYwMDM0OTQwMH0.ix0mFGS1XDXCBXH044f6B2JxutExbH2hZjGqZAwoHH8";

            TokenClaims claims = new TokenClaims()
            {
                UniqueId = 654321,
                Subject = "sip:.blindmelon-AppName-dev.jerky.@tla.vivox.com",
                From = "sip:blindmelon-AppName-dev-Admin@tla.vivox.com",
                Issuer = "blindmelon-AppName-dev",
                Action = "mute",
                To = "sip:confctl-g-blindmelon-AppName-dev.testchannel@tla.vivox.com",
                ExpiryTime = 1600349400
            };

            TokenGenerator generator = new TokenGenerator();
            var actualToken = generator.Generate(claims, "secret!");

            Assert.AreEqual(expectedToken, actualToken.Value);
        }

        /// <summary>
        /// Test values taken from:
        /// https://docs.vivox.com/v5/general/unity/15_1_160000/en-us/access-token-guide/access-token-examples/example-mute-all-token.htm
        /// </summary>
        [Test]
        public void UserMuteAllTest()
        {
            string expectedToken = "e30.eyJ2eGkiOjE5MjgzLCJmIjoic2lwOi5ibGluZG1lbG9uLUFwcE5hbWUtZGV2LmJlZWYuQHRsYS52aXZveC5jb20iLCJpc3MiOiJibGluZG1lbG9uLUFwcE5hbWUtZGV2IiwidnhhIjoibXV0ZSIsInQiOiJzaXA6Y29uZmN0bC1nLWJsaW5kbWVsb24tQXBwTmFtZS1kZXYudGVzdGNoYW5uZWxAdGxhLnZpdm94LmNvbSIsImV4cCI6MTYwMDM0OTQwMH0.fARLW2eX10ZbiIl_5WIg4bhPbYIhn2xfCcUNySfwBMs";
            var actualToken = _Generator.Generate(_Config, 19283, 1600349400, "beef", "mute", "g", "testchannel");
            Assert.AreEqual(expectedToken, actualToken.Value);
        }

        /// <summary>
        /// Test values taken from:
        /// https://docs.vivox.com/v5/general/unity/15_1_160000/en-us/access-token-guide/access-token-examples/example-mute-all-token.htm
        /// </summary>
        [Test]
        public void AdminMuteAllTest()
        {
            string expectedToken = "e30.eyJ2eGkiOjgyNTY0NywiZiI6InNpcDpibGluZG1lbG9uLUFwcE5hbWUtZGV2LUFkbWluQHRsYS52aXZveC5jb20iLCJpc3MiOiJibGluZG1lbG9uLUFwcE5hbWUtZGV2IiwidnhhIjoibXV0ZSIsInQiOiJzaXA6Y29uZmN0bC1nLWJsaW5kbWVsb24tQXBwTmFtZS1kZXYudGVzdGNoYW5uZWxAdGxhLnZpdm94LmNvbSIsImV4cCI6MTYwMDM0OTQwMH0.zpyvlBbVAKatuCeELb0Q1PsCb4tg0yacneL_sYHIVaw";

            TokenClaims claims = new TokenClaims()
            {
                UniqueId = 825647,
                From = "sip:blindmelon-AppName-dev-Admin@tla.vivox.com",
                Issuer = "blindmelon-AppName-dev",
                Action = "mute",
                To = "sip:confctl-g-blindmelon-AppName-dev.testchannel@tla.vivox.com",
                ExpiryTime = 1600349400
            };

            TokenGenerator generator = new TokenGenerator();
            var actualToken = generator.Generate(claims, "secret!");

            Assert.AreEqual(expectedToken, actualToken.Value);
        }

        /// <summary>
        /// Test values taken from:
        /// https://docs.vivox.com/v5/general/unity/15_1_160000/en-us/access-token-guide/access-token-examples/example-transcription-token.htm
        /// </summary>
        [Test]
        public void TranscriptionTest()
        {
            string expectedToken = "e30.eyJ2eGkiOjU0MjY4MCwiZiI6InNpcDouYmxpbmRtZWxvbi1BcHBOYW1lLWRldi5iZWVmLkB0bGEudml2b3guY29tIiwiaXNzIjoiYmxpbmRtZWxvbi1BcHBOYW1lLWRldiIsInZ4YSI6InRyeG4iLCJ0Ijoic2lwOmNvbmZjdGwtZy1ibGluZG1lbG9uLUFwcE5hbWUtZGV2LnRlc3RjaGFubmVsQHRsYS52aXZveC5jb20iLCJleHAiOjE2MDAzNDk0MDB9.-A0w_fcPCZaG5NMksnbSrGSVXNNt25YqlRjcKcLkGnA";
            var actualToken = _Generator.Generate(_Config, 542680, 1600349400, "beef", "trxn", "g", "testchannel");
            Assert.AreEqual(expectedToken, actualToken.Value);
        }
    }
}