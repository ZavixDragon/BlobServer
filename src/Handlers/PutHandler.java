package Handlers;

import IncomingRequests.PutRequest;
import IncomingResponses.LoginResponse;
import OutgoingRequests.LoginRequest;
import WebRequests.PostJsonWebRequest;
import rpc.RpcCallHandler;
import rpc.RpcRequestStatus;
import rpc.RpcResponse;

public class PutHandler extends RpcCallHandler<PutRequest, RpcResponse> {
    public PutHandler() {
        super("/put", PutRequest.class, x -> {
            LoginResponse login = (LoginResponse)new PostJsonWebRequest("url", new LoginRequest(x.Username, x.Password), LoginResponse.class).resolve();
            if (!login.ErrorMessage.equals(""))
                return new RpcResponse(x.RequestId, RpcRequestStatus.Error, login.ErrorMessage);

            return new RpcResponse(x.RequestId, RpcRequestStatus.Ok, "");
        });
    }
}
