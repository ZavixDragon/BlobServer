package Amazon.SmartValues;

import Amazon.Headers.Header;
import Amazon.OO.OOText.Text;
import Amazon.OO.Value;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class CanonicalHeaderNames extends Text {
    private final List<Header> headers;

    public CanonicalHeaderNames(List<Header> headers) {
        this.headers = headers;
    }

    public String get() {
        List<String> headerNames = headers.stream().filter(x -> !x.getKey().equals("Authorization")).map(x -> x.getKey()).collect(Collectors.toList());
        Collections.sort(headerNames, String.CASE_INSENSITIVE_ORDER);
        return String.join(";", headerNames).toLowerCase();
    }
}
