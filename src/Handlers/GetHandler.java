package Handlers;

import IncomingRequests.GetRequest;
import OutgoingResponses.GetResponse;
import rpc.RpcCallHandler;

public class GetHandler extends RpcCallHandler<GetRequest, GetResponse> {
    public GetHandler() {
        super("/get", GetRequest.class, x -> {
            return new GetResponse(x.RequestId, "");
        });
    }
}
