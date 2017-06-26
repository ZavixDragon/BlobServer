package Get;

import Amazon.AmazonGetWebRequest;
import rpc.RpcCallHandler;

public class GetHandler extends RpcCallHandler<GetRequest, GetResponse> {
    public GetHandler() {
        super("/get", GetRequest.class, x -> {
            return new GetResponse(x.RequestId, new AmazonGetWebRequest(x.Bucket, x.Id).resolve());
        });
    }
}
