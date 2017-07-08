package List;

import Amazon.OO.OOText.SimpleText;
import Amazon.Requests.AwsListRequest;
import rpc.RpcCallHandler;

import java.util.stream.Collectors;

public class ListKeysHandler extends RpcCallHandler<ListRequest, ListKeysResponse> {
    public ListKeysHandler() {
        super("/listkeys", ListRequest.class, x -> new ListKeysResponse(x.RequestId, new AwsListRequest(new SimpleText(x.Bucket)).get().stream().filter(y -> y.startsWith(x.Directory)).collect(Collectors.toList())));
    }
}
