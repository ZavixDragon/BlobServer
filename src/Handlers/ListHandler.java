package Handlers;

import IncomingRequests.ListRequest;
import OutgoingResponses.ListResponse;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.ObjectListing;
import rpc.RpcCallHandler;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ListHandler extends RpcCallHandler<ListRequest, ListResponse> {
    public ListHandler(AmazonS3 s3, String bucket) {
        super("/list", ListRequest.class, x -> {
            ListObjectsV2Result result = s3.listObjectsV2(new ListObjectsV2Request().withBucketName(bucket));
            return new ListResponse(x.RequestId, result.getObjectSummaries().stream().map(y -> y.getKey()).collect(Collectors.toList()));
        });
    }
}
