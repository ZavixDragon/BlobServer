package List;

import Amazon.AmazonListWebRequest;
import rpc.RpcCallHandler;

public class ListHandler extends RpcCallHandler<ListRequest, ListResponse> {
    public ListHandler() {
        super("/list", ListRequest.class, x -> {
            return new ListResponse(x.RequestId, new AmazonListWebRequest(x.Bucket).resolve());
        });
    }
}
