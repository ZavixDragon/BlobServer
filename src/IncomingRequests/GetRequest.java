package IncomingRequests;

import rpc.RpcRequest;

public final class GetRequest extends RpcRequest {
    public String Id;
    public String Bucket;

    public GetRequest(String id, String bucket) {
        Id = id;
        Bucket = bucket;
    }
}
