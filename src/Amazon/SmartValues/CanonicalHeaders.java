package Amazon.SmartValues;

import Amazon.OO.StringText;
import Amazon.OO.Text;
import Amazon.OO.Value;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class CanonicalHeaders extends Text {
    private final Map<Value<String>, Value<String>> headers;

    public CanonicalHeaders(Map<Value<String>, Value<String>> headers) {
        this.headers = headers;
    }

    public String get() {
        List<String> headerNames = headers.keySet().stream().filter(x -> !x.get().equals("Authorization")).map(x -> x.get()).collect(Collectors.toList());
        Collections.sort(headerNames, String.CASE_INSENSITIVE_ORDER);
        StringBuilder buffer = new StringBuilder();
        for (String key : headerNames) {
            String value = headers.get(new StringText(key)).get();
            buffer.append(key.toLowerCase().replaceAll("\\s+", " ") + ":" + value.replaceAll("\\s+", " "));
            buffer.append("\n");
        }
        return buffer.toString();
    }
}
