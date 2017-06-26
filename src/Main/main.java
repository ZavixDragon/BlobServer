package Main;

import Get.GetHandler;
import List.ListHandler;
import Put.PutHandler;
import Remove.RemoveHandler;

public class main {
    public static void main(String[] args) {
        new HttpJsonRpcApi(9039, new GetHandler(), new PutHandler(), new ListHandler(), new RemoveHandler()).start();
    }
}
