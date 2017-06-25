package Handlers;

import Amazon.AmazonGetWebRequest;
import Amazon.AmazonListWebRequest;
import IncomingRequests.ListRequest;
import OutgoingResponses.GetResponse;
import OutgoingResponses.ListResponse;
import rpc.RpcCallHandler;

import java.util.ArrayList;

public class ListHandler extends RpcCallHandler<ListRequest, ListResponse> {
    public ListHandler() {
        super("/list", ListRequest.class, x -> {
            return new ListResponse(x.RequestId, new AmazonListWebRequest(x.Bucket).resolve());
        });
    }
}
