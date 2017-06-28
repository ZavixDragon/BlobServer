package Get;

import rpc.RpcResponse;

public final class GetResponse extends RpcResponse {
    public final String Value;

    public GetResponse(String requestId, String value) {
        super(requestId);
        Value = value;
    }
}
