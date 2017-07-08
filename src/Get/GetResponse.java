package Get;

import rpc.RpcResponse;

public final class GetResponse extends RpcResponse {
    public final byte[] Value;

    public GetResponse(String requestId, byte[] value) {
        super(requestId);
        Value = value;
    }
}
