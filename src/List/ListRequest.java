package List;

import rpc.RpcRequest;

public class ListRequest extends RpcRequest {
    public String Bucket;
    public String Directory;

    public ListRequest(String bucket, String directory) {
        Bucket = bucket;
        Directory = directory;
    }
}
