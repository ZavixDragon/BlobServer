package Handlers;

import InputStreamToString;
import IncomingRequests.GetRequest;
import OutgoingResponses.GetResponse;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import rpc.RpcCallHandler;

import java.io.InputStream;
import java.io.InputStreamReader;

public class GetHandler extends RpcCallHandler<GetRequest, GetResponse> {
    public GetHandler(AmazonS3 s3) {
        super("/get", GetRequest.class, x -> {
            S3Object obj = s3.getObject(new GetObjectRequest("custom-magic-sets", x.getId()));
            InputStreamReader reader = new InputStreamReader(s3Ob)
            InputStream stream = obj..getObjectContent();
            return new GetResponse(x.RequestId, new InputStreamToString(obj.getObjectContent()));
        });
    }
}
