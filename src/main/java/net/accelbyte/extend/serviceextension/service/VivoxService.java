package net.accelbyte.extend.serviceextension.service;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.accelbyte.extend.serviceextension.GenerateVivoxTokenRequest;
import net.accelbyte.extend.serviceextension.GenerateVivoxTokenResponse;
import net.accelbyte.extend.serviceextension.ServiceGrpc;
import net.accelbyte.extend.serviceextension.vivox.ConfigProvider;
import net.accelbyte.extend.serviceextension.vivox.TokenData;
import net.accelbyte.extend.serviceextension.vivox.TokenGenerator;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@GRpcService
public class VivoxService extends ServiceGrpc.ServiceImplBase {

    private final Logger logger = LoggerFactory.getLogger(VivoxService.class);
    private final ConfigProvider vivoxConfig;
    private final TokenGenerator tokenGenerator;

    public VivoxService(ConfigProvider vivoxConfig, TokenGenerator tokenGenerator) {
        this.vivoxConfig = vivoxConfig;
        this.tokenGenerator = tokenGenerator;
    }

    protected String getChannelType(GenerateVivoxTokenRequest request) {
        String channelType = "";
        switch (request.getChannelType()) {
            case echo:
                channelType = "e";
                break;
            case positional:
                channelType = "p";
                break;
            case nonpositional:
                channelType = "g";
                break;
            default:
                throw new StatusRuntimeException(Status.INVALID_ARGUMENT.withDescription("Channel type is required but invalid."));
        }
        return channelType;
    }

    @Override
    public void generateVivoxToken(GenerateVivoxTokenRequest request, StreamObserver<GenerateVivoxTokenResponse> responseObserver) {
        TokenData token = null;
        try {
            switch (request.getType()) {
                case login:
                    token = tokenGenerator.generate(vivoxConfig, request.getUsername(), "login");
                    break;
                case join:
                    String joinCt = getChannelType(request);
                    token = tokenGenerator.generate(vivoxConfig, request.getUsername(), "join", joinCt, request.getChannelId());
                    break;
                case join_muted:
                    String joinMuteCt = getChannelType(request);
                    token = tokenGenerator.generate(vivoxConfig, request.getUsername(), "join_muted", joinMuteCt, request.getChannelId());
                    break;
                case kick:
                    String kickCt = getChannelType(request);
                    token = tokenGenerator.generate(vivoxConfig, request.getUsername(), "kick", kickCt, request.getChannelId(), request.getTargetUsername());
                    break;
                default:
                    throw new StatusRuntimeException(Status.INVALID_ARGUMENT.withDescription("Invalid request type"));
            }

            if (token == null) {
                throw new StatusRuntimeException(Status.INVALID_ARGUMENT.withDescription("Invalid request type"));
            }

            GenerateVivoxTokenResponse response = GenerateVivoxTokenResponse.newBuilder()
                    .setUri(token.getClaims().getTo())
                    .setAccessToken(token.getValue())
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(new StatusRuntimeException(Status.INVALID_ARGUMENT.withDescription(e.getMessage())));
        }
    }
}