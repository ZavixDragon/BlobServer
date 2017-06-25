package Main;

import Handlers.GetHandler;
import Handlers.ListHandler;
import Handlers.PutHandler;
import Handlers.RemoveHandler;

public class main {
    public static void main(String[] args) {
        new HttpJsonRpcApi(9039, new GetHandler(), new PutHandler(), new ListHandler(), new RemoveHandler()).start();
    }
}
