package List;

import Get.GetHandler;
import Get.GetRequest;
import rpc.RpcCallHandler;

import java.util.stream.Collectors;

public final class ListValuesHandler extends RpcCallHandler<ListRequest, ListValuesResponse> {
    public ListValuesHandler(ListKeysHandler listKeys, GetHandler get) {
        super("/listvalues", ListRequest.class, x ->
                new ListValuesResponse(x.RequestId, listKeys.getResponse(x).Keys.stream().map(y -> get.getResponse(new GetRequest(x.Bucket, y)).Value).collect(Collectors.toList())));
    }
}
