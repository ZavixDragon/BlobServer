package IncomingRequests;

import rpc.RpcRequest;

public class ListRequest extends RpcRequest {
    public String Bucket;

    public ListRequest(String bucket) {
        this.Bucket = bucket;
    }
}
