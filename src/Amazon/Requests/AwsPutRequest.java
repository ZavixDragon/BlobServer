package Amazon.Requests;

import Amazon.Headers.ContentLengthHeader;
import Amazon.Headers.Header;
import Amazon.OO.CacheValue;
import Amazon.OO.OOBytes.Bytes;
import Amazon.OO.OOBytes.TextAsBytes;
import Amazon.OO.OOText.SimpleText;
import Amazon.OO.OOText.Text;
import Amazon.OO.Value;
import Amazon.Requests.URLs.PutBucketUrl;
import Amazon.Requests.URLs.PutUrl;

import java.net.URL;
import java.util.List;
import java.util.function.Supplier;

public final class AwsPutRequest extends Bytes {
    private final Text bucket;
    private final Text id;
    private final Bytes content;

    public AwsPutRequest(Text bucket, Text id, Bytes content) {
        this.bucket = bucket;
        this.id = id;
        this.content = content;
    }

    public byte[] get() {
        EnsureBucketExists();
        Text region = new SimpleText("us-west-2");
        Value<URL> url = new PutUrl(bucket, id, region);
        Text httpMethod = new SimpleText("PUT");
        List<Header> headers = new AwsHeaders(url, httpMethod, region, content).get();
        headers.add(new ContentLengthHeader(content));
        return new AwsHttpPutRequest(url ,httpMethod, new CacheValue<>((Supplier<List<Header>>)() -> headers), content).get();
    }

    private void EnsureBucketExists() {
        Value<URL> url = new PutBucketUrl(bucket);
        Text httpMethod = new SimpleText("PUT");
        Bytes content = new TextAsBytes(new SimpleText("<CreateBucketConfiguration xmlns=\"http://s3.amazonaws.com/doc/2006-03-01/\"><LocationConstraint>us-west-2</LocationConstraint></CreateBucketConfiguration>"));
        List<Header> headers = new AwsHeaders(url, httpMethod, new SimpleText("us-east-1"), content).get();
        headers.add(new ContentLengthHeader(content));
        new AwsHttpPutRequest(url, httpMethod, new CacheValue<>((Supplier<List<Header>>)() -> headers), content).get();
    }
}
