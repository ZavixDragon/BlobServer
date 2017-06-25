package IncomingRequests;

import rpc.RpcRequest;

public final class PutRequest extends RpcRequest {
    public String Username;
    public String Password;
    public String Bucket;
    public String Id;
    public String Value;

    public PutRequest(String username, String password, String bucket, String id, String value) {
        Username = username;
        Password = password;
        Bucket = bucket;
        Id = id;
        Value = value;
    }
}
