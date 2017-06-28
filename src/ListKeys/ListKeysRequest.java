package ListKeys;

import rpc.RpcRequest;

public class ListKeysRequest extends RpcRequest {
    public String Bucket;
    public String Directory;

    public ListKeysRequest(String bucket, String directory) {
        Bucket = bucket;
        Directory = directory;
    }
}
