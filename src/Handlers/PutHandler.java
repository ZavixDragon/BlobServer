package Handlers;

import IncomingRequests.PutRequest;
import IncomingResponses.LoginResponse;
import OutgoingRequests.LoginRequest;
import WebRequests.PostJsonWebRequest;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import rpc.RpcCallHandler;
import rpc.RpcRequestStatus;
import rpc.RpcResponse;

public class PutHandler extends RpcCallHandler<PutRequest, RpcResponse> {
    public PutHandler(AmazonS3 s3, String bucket) {
        super("/put", PutRequest.class, x -> {
            LoginResponse login = (LoginResponse)new PostJsonWebRequest("url", new LoginRequest(x.Username, x.Password), LoginResponse.class).resolve();
            if (!login.ErrorMessage.equals(""))
                return new RpcResponse(x.RequestId, RpcRequestStatus.Error, login.ErrorMessage);
            s3.putObject(new PutObjectRequest(bucket, x.Id, x.Content));
            return new RpcResponse(x.RequestId, RpcRequestStatus.Ok, "");
        });
    }
}
