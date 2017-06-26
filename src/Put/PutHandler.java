package Put;

import Amazon.AmazonPutWebRequest;
import MinAuth.LoginResponse;
import MinAuth.LoginRequest;
import WebRequests.PostJsonWebRequest;
import WebRequests.StringAsUrl;
import rpc.RpcCallHandler;
import rpc.RpcRequestStatus;
import rpc.RpcResponse;

public class PutHandler extends RpcCallHandler<PutRequest, RpcResponse> {
    public PutHandler() {
        super("/put", PutRequest.class, x -> {
            LoginResponse login = (LoginResponse)new PostJsonWebRequest(new StringAsUrl("https://miniauth.azurewebsites.net/api/account/login").get(),
                    new LoginRequest(x.Username, x.Password),
                    LoginResponse.class).resolve();
            if (!login.ErrorMessage.equals(""))
                return new RpcResponse(x.RequestId, RpcRequestStatus.Error, login.ErrorMessage);
            return new RpcResponse(x.RequestId, RpcRequestStatus.Ok, new AmazonPutWebRequest(x.Bucket, x.Id, x.Content).resolve());
        });
    }
}
