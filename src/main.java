import Handlers.GetHandler;
import Handlers.ListHandler;
import Handlers.PutHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class main {
    public static void main(String[] args) {
        AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        new HttpJsonRpcApi(9039, new GetHandler(s3), new PutHandler(), new ListHandler()).start();
    }
}
