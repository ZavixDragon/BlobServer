import Amazon.OO.OOBytes.TextAsBytes;
import Amazon.OO.OOText.SimpleText;
import Get.GetRequest;
import List.ListKeysResponse;
import List.ListRequest;
import Put.PutRequest;
import Remove.RemoveRequest;
import Main.main;
import Get.GetResponse;
import List.ListValuesResponse;
import WebRequests.PostJsonWebRequest;
import WebRequests.StringAsUrl;
import org.junit.Assert;
import org.junit.Test;
import rpc.RpcRequestStatus;
import rpc.RpcResponse;

public class GoldTests {
    private final String bucket = "test-bucket-999";

    @Test
    public void Init() {
        main.main(new String[] {});

        AddRemove();
        AddGetRemove();
        ListKeysEmpty();
        ListValuesEmpty();
        AddListKeysRemove();
        AddListValuesRemove();
        AddRemoveListKeys();
        AddRemoveListValues();
        AddAddListKeysInDirectoryRemoveRemove();
        AddAddListValuesInDirectoryRemoveRemove();
    }

    public void AddRemove() {
        Assert.assertEquals(RpcRequestStatus.Ok, putObjectInS3("test-object", "test-value").Status);
        Assert.assertEquals(RpcRequestStatus.Ok, removeObjectInS3("test-object").Status);
    }

    public void AddGetRemove() {
        Assert.assertEquals(RpcRequestStatus.Ok, putObjectInS3("test-object", "test-value").Status);
        Assert.assertEquals("test-value", new String(getObjectOutS3("test-object").Value));
        Assert.assertEquals(RpcRequestStatus.Ok, removeObjectInS3("test-object").Status);
    }

    public void ListKeysEmpty() {
        Assert.assertEquals(0, listKeysInS3().Keys.size());
    }

    public void ListValuesEmpty() {
        Assert.assertEquals(0, listValuesInS3().Values.size());
    }

    public void AddListKeysRemove() {
        Assert.assertEquals(RpcRequestStatus.Ok, putObjectInS3("test-object", "test-value").Status);
        Assert.assertEquals(1, listKeysInS3().Keys.size());
        Assert.assertEquals("test-object", new String(listKeysInS3().Keys.get(0)));
        Assert.assertEquals(RpcRequestStatus.Ok, removeObjectInS3("test-object").Status);
    }

    public void AddListValuesRemove() {
        Assert.assertEquals(RpcRequestStatus.Ok, putObjectInS3("test-object", "test-value").Status);
        Assert.assertEquals(1, listValuesInS3().Values.size());
        Assert.assertEquals("test-value", new String(listValuesInS3().Values.get(0)));
        Assert.assertEquals(RpcRequestStatus.Ok, removeObjectInS3("test-object").Status);
    }

    public void AddRemoveListKeys() {
        Assert.assertEquals(RpcRequestStatus.Ok, putObjectInS3("test-object", "test-value").Status);
        Assert.assertEquals(RpcRequestStatus.Ok, removeObjectInS3("test-object").Status);
        Assert.assertEquals(0, listKeysInS3().Keys.size());
    }

    public void AddRemoveListValues() {
        Assert.assertEquals(RpcRequestStatus.Ok, putObjectInS3("test-object", "test-value").Status);
        Assert.assertEquals(RpcRequestStatus.Ok, removeObjectInS3("test-object").Status);
        Assert.assertEquals(0, listValuesInS3().Values.size());
    }

    public void AddAddListKeysInDirectoryRemoveRemove() {
        Assert.assertEquals(RpcRequestStatus.Ok, putObjectInS3("test-object", "test-value").Status);
        Assert.assertEquals(RpcRequestStatus.Ok, putObjectInS3("test-directory/test-object", "test-value").Status);
        Assert.assertEquals(1, listKeysInS3SubDirectory("test-directory").Keys.size());
        Assert.assertEquals("test-directory/test-object", new String(listKeysInS3SubDirectory("test-directory").Keys.get(0)));
        Assert.assertEquals(RpcRequestStatus.Ok, removeObjectInS3("test-object").Status);
        Assert.assertEquals(RpcRequestStatus.Ok, removeObjectInS3("test-directory/test-object").Status);
    }

    public void AddAddListValuesInDirectoryRemoveRemove() {
        Assert.assertEquals(RpcRequestStatus.Ok, putObjectInS3("test-object", "test-value").Status);
        Assert.assertEquals(RpcRequestStatus.Ok, putObjectInS3("test-directory/test-object", "test-value").Status);
        Assert.assertEquals(1, listValuesInS3SubDirectory("test-directory").Values.size());
        Assert.assertEquals("test-value", new String(listValuesInS3SubDirectory("test-directory").Values.get(0)));
        Assert.assertEquals(RpcRequestStatus.Ok, removeObjectInS3("test-object").Status);
        Assert.assertEquals(RpcRequestStatus.Ok, removeObjectInS3("test-directory/test-object").Status);
    }

    public RpcResponse putObjectInS3(String id, String value) {
        return new PostJsonWebRequest<>(new StringAsUrl("http://localhost:9039/put").get(),
                new PutRequest("ZavixDragon", "neverever9", bucket, id, new TextAsBytes(new SimpleText(value)).get()),
                RpcResponse.class).resolve();
    }

    private GetResponse getObjectOutS3(String id) {
        return new PostJsonWebRequest<>(new StringAsUrl("http://localhost:9039/get").get(),
                new GetRequest(bucket, id),
                GetResponse.class).resolve();
    }

    private RpcResponse removeObjectInS3(String id) {
        return new PostJsonWebRequest<>(new StringAsUrl("http://localhost:9039/remove").get(),
                new RemoveRequest("ZavixDragon", "neverever9", bucket, id),
                RpcResponse.class).resolve();
    }

    private ListKeysResponse listKeysInS3() {
        return new PostJsonWebRequest<>(new StringAsUrl("http://localhost:9039/listkeys").get(),
                new ListRequest(bucket, ""),
                ListKeysResponse.class).resolve();
    }

    private ListKeysResponse listKeysInS3SubDirectory(String directory) {
        return new PostJsonWebRequest<>(new StringAsUrl("http://localhost:9039/listkeys").get(),
                new ListRequest(bucket, directory),
                ListKeysResponse.class).resolve();
    }

    private ListValuesResponse listValuesInS3() {
        return new PostJsonWebRequest<>(new StringAsUrl("http://localhost:9039/listvalues").get(),
                new ListRequest(bucket, ""),
                ListValuesResponse.class).resolve();
    }

    private ListValuesResponse listValuesInS3SubDirectory(String directory) {
        return new PostJsonWebRequest<>(new StringAsUrl("http://localhost:9039/listvalues").get(),
                new ListRequest(bucket, directory),
                ListValuesResponse.class).resolve();
    }
}
