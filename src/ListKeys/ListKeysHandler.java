package ListKeys;

import Amazon.AmazonListWebRequest;
import rpc.RpcCallHandler;

import java.util.stream.Collectors;

public class ListKeysHandler extends RpcCallHandler<ListKeysRequest, ListKeysResponse> {
    public ListKeysHandler() {
        super("/listkeys", ListKeysRequest.class, x -> {
            return new ListKeysResponse(x.RequestId, new AmazonListWebRequest(x.Bucket).resolve().stream().filter(y -> y.startsWith(x.Directory)).collect(Collectors.toList()));
        });
    }
}
