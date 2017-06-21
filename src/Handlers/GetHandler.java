package Handlers;

import Common.InputStreamToString;
import IncomingRequests.GetRequest;
import OutgoingResponses.GetResponse;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import rpc.RpcCallHandler;

public class GetHandler extends RpcCallHandler<GetRequest, GetResponse> {
    public GetHandler(AmazonS3 s3, String bucket) {
        super("/get", GetRequest.class, x -> {
            try {
                S3Object obj = s3.getObject(new GetObjectRequest(bucket, x.getId()));
                String result = new InputStreamToString(obj.getObjectContent()).get();
                obj.getObjectContent().close();
                return new GetResponse(x.RequestId, result);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
