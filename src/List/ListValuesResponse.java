package List;

import rpc.RpcResponse;

import java.util.List;

public class ListValuesResponse extends RpcResponse {
    public final List<byte[]> Values;

    public ListValuesResponse(String requestId, List<byte[]> values) {
        super(requestId);
        Values = values;
    }
}
