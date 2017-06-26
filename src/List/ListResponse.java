package List;

import rpc.RpcResponse;

import java.util.List;

public class ListResponse extends RpcResponse {
    public final List<String> JsonValues;

    public ListResponse(String requestId, List<String> jsonValues) {
        super(requestId);
        JsonValues = jsonValues;
    }
}
