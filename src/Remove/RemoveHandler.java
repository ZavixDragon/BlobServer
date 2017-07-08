package Remove;

import Amazon.OO.OOText.SimpleText;
import Amazon.Requests.AwsRemoveRequest;
import MinAuth.LoginResponse;
import MinAuth.LoginRequest;
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
            new AwsRemoveRequest(new SimpleText(x.Bucket), new SimpleText(x.Id)).get();
            return new RpcResponse(x.RequestId, RpcRequestStatus.Ok, "");
        });
    }
}
