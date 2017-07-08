package Amazon.Requests;

import Amazon.OO.CacheValue;
import Amazon.OO.OOBytes.Bytes;
import Amazon.OO.OOText.SimpleText;
import Amazon.OO.OOText.Text;
import Amazon.OO.Value;
import Amazon.Requests.URLs.ListUrl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public final class AwsListRequest implements Value<List<String>> {
    private final Text bucket;

    public AwsListRequest(Text bucket) {
        this.bucket = bucket;
    }

    public List<String> get() {
        Value<URL> url = new CacheValue<>(new ListUrl(bucket));
        Text httpMethod = new SimpleText("GET");
        return getKeys(new AwsHttpGetRequest(url, httpMethod, new AwsHeaders(url, httpMethod, new SimpleText("us-west-2"))));
    }

    private List<String> getKeys(Bytes result) {
        String toSearch = new String(result.get());
        List<String> hits = new ArrayList<String>();
        while(toSearch.contains("<Key>")) {
            hits.add(toSearch.substring(toSearch.indexOf("<Key>") + 5, toSearch.indexOf("</Key>")));
            toSearch = toSearch.substring(toSearch.indexOf("</Key>") + 6);
        }
        return hits;
    }
}
