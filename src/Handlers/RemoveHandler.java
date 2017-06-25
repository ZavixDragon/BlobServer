package Handlers;

import Amazon.AmazonRemoveWebRequest;
import IncomingRequests.RemoveRequest;
import IncomingResponses.LoginResponse;
import OutgoingRequests.LoginRequest;
import WebRequests.PostJsonWebRequest;
import WebRequests.StringAsUrl;
import rpc.RpcCallHandler;
import rpc.RpcRequestStatus;
import rpc.RpcResponse;

public class RemoveHandler extends RpcCallHandler<RemoveRequest, RpcResponse> {
    public RemoveHandler() {
        super("/remove", RemoveRequest.class, x -> {
            LoginResponse login = (LoginResponse)new PostJsonWebRequest(new StringAsUrl("https://miniauth.azurewebsites.net/api/account/login").get(),
                    new LoginRequest(x.Username, x.Password),
                    LoginResponse.class).resolve();
            if (!login.ErrorMessage.equals(""))
                return new RpcResponse(x.RequestId, RpcRequestStatus.Error, login.ErrorMessage);
            return new RpcResponse(x.RequestId, RpcRequestStatus.Ok, new AmazonRemoveWebRequest(x.Bucket, x.Id).resolve());
        });
    }
}
