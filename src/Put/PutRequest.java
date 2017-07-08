package Put;

import rpc.RpcRequest;

public final class PutRequest extends RpcRequest {
    public String Username;
    public String Password;
    public String Bucket;
    public String Id;
    public byte[] Content;

    public PutRequest(String username, String password, String bucket, String id, byte[] content) {
        Username = username;
        Password = password;
        Bucket = bucket;
        Id = id;
        Content = content;
    }
}
