package List;

import rpc.RpcResponse;

import java.util.List;

public final class ListKeysResponse extends RpcResponse {
    public final List<String> Keys;

    public ListKeysResponse(String requestId, List<String> keys) {
        super(requestId);
        Keys = keys;
    }
}
