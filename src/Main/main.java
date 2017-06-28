package Main;

import Get.GetHandler;
import ListKeys.ListKeysHandler;
import Put.PutHandler;
import Remove.RemoveHandler;

public class main {
    public static void main(String[] args) {
        new HttpJsonRpcApi(9039, new GetHandler(), new PutHandler(), new ListKeysHandler(), new RemoveHandler()).start();
    }
}
