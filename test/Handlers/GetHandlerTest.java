package Handlers;

import IncomingRequests.GetRequest;
import Main.main;
import OutgoingResponses.GetResponse;
import WebRequests.PostJsonWebRequest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class GetHandlerTest {
    @BeforeClass
    public static void Setup() {
        main.main(new String[] {});
    }

    @Test
    public void getObjectFromS3() {
        GetResponse response = new PostJsonWebRequest<GetRequest, GetResponse>("http://localhost:9039/get", new GetRequest("id.txt", "custom-magic-sets"), GetResponse.class).resolve();

        Assert.assertEquals("blah blah", response.JsonValue);
    }

    @Test
    public void GetNonExistentObjectFromS3() {

    }
}
