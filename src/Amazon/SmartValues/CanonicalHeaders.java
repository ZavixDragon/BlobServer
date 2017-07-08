package Amazon.SmartValues;

import Amazon.Headers.Header;
import Amazon.OO.OOText.SimpleText;
import Amazon.OO.OOText.Text;
import Amazon.OO.Value;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class CanonicalHeaders extends Text {
    private final List<Header> headers;

    public CanonicalHeaders(List<Header> headers) {
        this.headers = headers;
    }

    public String get() {
        List<String> headerNames = headers.stream().filter(x -> !x.getKey().equals("Authorization")).map(x -> x.getKey()).collect(Collectors.toList());
        Collections.sort(headerNames, String.CASE_INSENSITIVE_ORDER);
        StringBuilder buffer = new StringBuilder();
        for (String key : headerNames) {
            String value = headers.stream().filter(x -> x.getKey().equals(key)).collect(Collectors.toList()).get(0).getValue();
            buffer.append(key.toLowerCase().replaceAll("\\s+", " ") + ":" + value.replaceAll("\\s+", " "));
            buffer.append("\n");
        }
        return buffer.toString();
    }
}
