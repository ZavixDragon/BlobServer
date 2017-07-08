package Amazon.Requests;

import Amazon.OO.OOBytes.Bytes;

import java.io.*;
import java.net.HttpURLConnection;

public final class AwsConnectionResponse extends Bytes {
    private final HttpURLConnection connection;

    public AwsConnectionResponse(HttpURLConnection connection) {
        this.connection = connection;
    }

    public byte[] get() {
        try {
            InputStream is;
            try {
                is = connection.getInputStream();
            } catch (IOException e) {
                is = connection.getErrorStream();
            }
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            boolean isFirstLine = true;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                if (!isFirstLine)
                    response.append('\r');
                response.append(line);
                isFirstLine = false;
            }
            rd.close();
            return response.toString().getBytes();

            /*ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = is.read(data, 0, data.length)) != -1)
                buffer.write(data, 0, nRead);
            buffer.flush();
            return buffer.toByteArray();*/
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null)
                connection.disconnect();
        }
    }
}
