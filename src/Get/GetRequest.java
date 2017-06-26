package Get;

import rpc.RpcRequest;

public final class GetRequest extends RpcRequest {
    public String Bucket;
    public String Id;

    public GetRequest(String bucket, String id) {
        Bucket = bucket;
        Id = id;
    }
}
