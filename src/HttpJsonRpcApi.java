import com.google.gson.Gson;
import rpc.RpcCallHandler;
import rpc.RpcRequest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HttpJsonRpcApi {
    private final HttpServer server;
    private final Map<String, RpcCallHandler> callMap = new HashMap<>();
    private final Gson gson = new Gson();

    public HttpJsonRpcApi(int port, RpcCallHandler... calls) {
        server = new HttpServer(port);
        Arrays.stream(calls).forEach(x -> callMap.put(x.getUri(), x));
        server.setServeFunction(this::serve);
    }

    public void start() {
        server.start();
    }

    public void stop() {
        server.stop();
    }

    private NanoHTTPD.Response serve(NanoHTTPD.IHTTPSession session) {
        RpcCallHandler handler = callMap.get(session.getUri());
        String content = new InputStreamToString(session.getInputStream()).get();
        RpcRequest request = (RpcRequest)gson.fromJson(content, handler.getRequestType());
        String response = gson.toJson(handler.getResponse(request));
        return NanoHTTPD.newFixedLengthResponse(NanoHTTPD.Response.Status.OK, "application/json", response);
    }
}
