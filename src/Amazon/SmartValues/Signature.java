package Amazon.SmartValues;

import Amazon.OO.OOBytes.Bytes;
import Amazon.OO.OOBytes.TextAsBytes;
import Amazon.OO.OOText.Concat;
import Amazon.OO.OOText.Replace;
import Amazon.OO.OOText.SimpleText;
import Amazon.OO.OOText.Text;
import Amazon.SimpleValues.DateStamp;
import Amazon.Utilities.SignHmac;

public final class Signature extends Bytes {
    private final Bytes value;

    public Signature(Text algorithm, Text textToSign, Text termination, Text serviceName, Text region, DateStamp stamp, Text scheme, Text secretKey) {
        algorithm = new Replace(algorithm, new SimpleText("-"), new SimpleText(""));
        value = new SignHmac(
                    algorithm,
                    textToSign,
                    new SignHmac(
                            algorithm,
                            termination,
                            new SignHmac(
                                    algorithm,
                                    serviceName,
                                    new SignHmac(
                                            algorithm,
                                            region,
                                            new SignHmac(
                                                    algorithm,
                                                    new SimpleText(stamp.getDateStamp()),
                                                    new TextAsBytes(
                                                            new Concat(scheme, secretKey)))))));
    }

    public byte[] get() {
        return value.get();
    }
}
