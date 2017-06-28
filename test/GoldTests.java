import Get.GetRequest;
import ListKeys.ListKeysRequest;
import Put.PutRequest;
import Remove.RemoveRequest;
import Main.main;
import Get.GetResponse;
import ListKeys.ListKeysResponse;
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
        Assert.assertEquals(RpcRequestStatus.Ok, putObjectInS3("test-directory/test-object", "test-value\r").Status);
        Assert.assertEquals("test-value\r", getObjectOutS3("test-object").Value);
        Assert.assertEquals(true, listKeysInS3().Keys.contains("test-object"));
        Assert.assertEquals(false, listKeysInS3SubDirectory("test-directory").Keys.contains("test-object"));
        Assert.assertEquals(true, listKeysInS3SubDirectory("test-directory").Keys.contains("test-directory/test-object"));
        Assert.assertEquals(RpcRequestStatus.Ok, removeObjectInS3("test-object").Status);
        Assert.assertEquals(false, listKeysInS3().Keys.contains("test-object"));
        Assert.assertEquals(RpcRequestStatus.Ok, removeObjectInS3("test-directory/test-object").Status);
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

    private ListKeysResponse listKeysInS3() {
        return new PostJsonWebRequest<>(new StringAsUrl("http://localhost:9039/listkeys").get(),
                new ListKeysRequest(bucket, ""),
                ListKeysResponse.class).resolve();
    }

    private RpcResponse removeObjectInS3(String id) {
        return new PostJsonWebRequest<>(new StringAsUrl("http://localhost:9039/remove").get(),
                new RemoveRequest("ZavixDragon", "neverever9", bucket, id),
                RpcResponse.class).resolve();
    }

    private ListKeysResponse listKeysInS3SubDirectory(String directory) {
        return new PostJsonWebRequest<>(new StringAsUrl("http://localhost:9039/listkeys").get(),
                new ListKeysRequest(bucket, directory),
                ListKeysResponse.class).resolve();
    }
}
