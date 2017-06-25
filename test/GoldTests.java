import IncomingRequests.GetRequest;
import IncomingRequests.ListRequest;
import IncomingRequests.PutRequest;
import IncomingRequests.RemoveRequest;
import Main.main;
import OutgoingResponses.GetResponse;
import OutgoingResponses.ListResponse;
import WebRequests.PostJsonWebRequest;
import WebRequests.StringAsUrl;
import org.junit.Assert;
import org.junit.Test;
import rpc.RpcRequestStatus;
import rpc.RpcResponse;

public class GoldTests {
    private final String bucket = "test-bucket-999";

    @Test
    public void Gold() {
        main.main(new String[] {});

        Assert.assertEquals(RpcRequestStatus.Ok, putObjectInS3("test-object", "test-value\r").Status);
        Assert.assertEquals("test-value\r", getObjectOutS3("test-object").JsonValue);
        Assert.assertEquals(true, listObjectsInS3().JsonValues.contains("test-object"));
        Assert.assertEquals(RpcRequestStatus.Ok, removeObjectInS3("test-object").Status);
        Assert.assertEquals(false, listObjectsInS3().JsonValues.contains("test-object"));
    }

    public RpcResponse putObjectInS3(String id, String value) {
        return new PostJsonWebRequest<>(new StringAsUrl("http://localhost:9039/put").get(),
                new PutRequest("ZavixDragon", "neverever9", bucket, id, value),
                RpcResponse.class).resolve();
    }

    private GetResponse getObjectOutS3(String id) {
        return new PostJsonWebRequest<>(new StringAsUrl("http://localhost:9039/get").get(),
                new GetRequest(bucket, id),
                GetResponse.class).resolve();
    }

    private ListResponse listObjectsInS3() {
        return new PostJsonWebRequest<>(new StringAsUrl("http://localhost:9039/list").get(),
                new ListRequest(bucket),
                ListResponse.class).resolve();
    }

    private RpcResponse removeObjectInS3(String id) {
        return new PostJsonWebRequest<>(new StringAsUrl("http://localhost:9039/remove").get(),
                new RemoveRequest("ZavixDragon", "neverever9", bucket, id),
                RpcResponse.class).resolve();
    }
}
