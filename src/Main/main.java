package Main;

import Get.GetHandler;
import List.ListKeysHandler;
import List.ListValuesHandler;
import Put.PutHandler;
import Remove.RemoveHandler;

public class main {
    public static void main(String[] args) {
        ListKeysHandler listKeys = new ListKeysHandler();
        GetHandler get = new GetHandler();
        new HttpJsonRpcApi(9039, get, new PutHandler(), listKeys, new RemoveHandler(), new ListValuesHandler(listKeys, get)).start();
    }
}
