package Amazon.SmartValues;

import Amazon.OO.Text;
import Amazon.OO.Value;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class CanonicalHeaderNames extends Text {
    private final Map<Value<String>, Value<String>> headers;

    public CanonicalHeaderNames(Map<Value<String>, Value<String>> headers) {
        this.headers = headers;
    }

    public String get() {
        List<String> headerNames = headers.keySet().stream().map(x -> x.get()).collect(Collectors.toList());
        Collections.sort(headerNames, String.CASE_INSENSITIVE_ORDER);
        return String.join(";", headerNames).toLowerCase();
    }
}
