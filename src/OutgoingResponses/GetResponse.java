package OutgoingResponses;

import rpc.RpcResponse;

public final class GetResponse extends RpcResponse {
    public final String JsonValue;

    public GetResponse(String requestId, String jsonValue) {
        super(requestId);
        JsonValue = jsonValue;
    }
}
