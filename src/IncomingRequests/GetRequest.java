package IncomingRequests;

import rpc.RpcRequest;

public final class GetRequest extends RpcRequest {
    private String id;

    public String getId() { return id; }
}
