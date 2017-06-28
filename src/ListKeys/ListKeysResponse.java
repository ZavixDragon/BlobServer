package ListKeys;

import rpc.RpcResponse;

import java.util.List;

public class ListKeysResponse extends RpcResponse {
    public final List<String> Keys;

    public ListKeysResponse(String requestId, List<String> keys) {
        super(requestId);
        Keys = keys;
    }
}
