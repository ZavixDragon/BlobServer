package Amazon;

import Amazon.OO.Value;

import java.util.ArrayList;
import java.util.List;

public class AmazonListResponse implements Value<List<String>> {
    private final String result;

    public AmazonListResponse(String result) {
        this.result = result;
    }

    public List<String> get() {
        String toSearch = result;
        List<String> hits = new ArrayList<String>();
        while(toSearch.contains("<Key>")) {
            hits.add(toSearch.substring(toSearch.indexOf("<Key>") + 5, toSearch.indexOf("</Key>")));
            toSearch = toSearch.substring(toSearch.indexOf("</Key>") + 6);
        }
        return hits;
    }
}
