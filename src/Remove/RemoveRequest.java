package Remove;

import rpc.RpcRequest;

public class RemoveRequest extends RpcRequest {
    public String Username;
    public String Password;
    public String Bucket;
    public String Id;

    public RemoveRequest(String username, String password, String bucket, String id) {
        Username = username;
        Password = password;
        Bucket = bucket;
        Id = id;
    }
}
