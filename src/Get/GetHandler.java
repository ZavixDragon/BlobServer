package Get;

import Amazon.OO.OOText.SimpleText;
import Amazon.Requests.AwsGetRequest;
import rpc.RpcCallHandler;

public class GetHandler extends RpcCallHandler<GetRequest, GetResponse> {
    public GetHandler() {
        super("/get", GetRequest.class, x ->
                new GetResponse(x.RequestId, new AwsGetRequest(new SimpleText(x.Bucket), new SimpleText(x.Id)).get()));
    }
}
