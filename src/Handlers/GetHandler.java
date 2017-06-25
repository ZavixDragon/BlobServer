package Handlers;

import Amazon.AmazonGetWebRequest;
import IncomingRequests.GetRequest;
import OutgoingResponses.GetResponse;
import rpc.RpcCallHandler;

public class GetHandler extends RpcCallHandler<GetRequest, GetResponse> {
    public GetHandler() {
        super("/get", GetRequest.class, x -> {
            return new GetResponse(x.RequestId, new AmazonGetWebRequest(x.Bucket, x.Id).resolve());
        });
    }
}
