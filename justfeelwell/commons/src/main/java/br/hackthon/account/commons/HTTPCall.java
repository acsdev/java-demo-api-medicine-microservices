package br.hackthon.account.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Map;

public class HTTPCall {

    private static final Logger LOG = LoggerFactory.getLogger(HTTPCall.class);

    /**
     * Do an request GET
     *
     * @param url
     *
     * @return Body of HTTP response as JSON
     */
    public static String doGetAndReturnJson(final String url) {
        return doGetAndReturnJson( url, null );
    }
    /**
     * Do an request GET
     *
     * @param url
     * @param headerValues map with attributes to add on header
     *
     * @return Body of HTTP response as JSON
     */
    public static String doGetAndReturnJson(final String url, final Map<String, String> headerValues) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000); // 5 Seconds
            conn.setDoInput(true);
            conn.setDoOutput(false);
            conn.setUseCaches(false);

            //HEADER VALUES
            conn.setRequestProperty("Accept", "application/json");
            if (headerValues != null && !headerValues.isEmpty()) {
                headerValues.forEach( (k,v) -> conn.setRequestProperty(k, v) );
            }

            String response = HTTPCall.prepareResponse(conn);

            return response;
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    /**
     * Do an request POST
     *
     * @param url       HTTP url
     * @param inputJSON input data
     * @return Body of HTTP response as JSON
     */
    public static String doPostAndReturnJson(final String url, final String inputJSON) {
        return doPostAndReturnJson( url, inputJSON, null );
    }

    /**
     * Do an request POST
     *
     * @param url       HTTP url
     * @param inputJSON input data
     * @param headerValues map with attributes to add on header
     * @return Body of HTTP response as JSON
     */
    public static String doPostAndReturnJson(final String url, final String inputJSON, final Map<String, String> headerValues) {
        try {

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5 * 1000); // 5 Seconds
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);

            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            // HEADER VALUES
            conn.setRequestProperty("Accept", "application/json");
            if (headerValues != null && !headerValues.isEmpty()) {
                headerValues.forEach( (k,v) -> conn.setRequestProperty(k, v) );
            }

            DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
            outStream.writeBytes(inputJSON);
            outStream.flush();
            outStream.close();

            String response = HTTPCall.prepareResponse(conn);

            return response;
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    private static String prepareResponse(HttpURLConnection conn) throws IOException {
        conn.connect();
        try {
            StringBuilder json = new StringBuilder();
            DataInputStream responseStream = new DataInputStream(conn.getInputStream());
            int lidos = 0;
            byte[] buffer = new byte[4096];
            while (true) {
                lidos = responseStream.read(buffer);
                if (lidos <= 0) {
                    break;
                }
                if (conn.getContentEncoding() != null) {
                    json.append(new String(buffer, 0, lidos, conn.getContentEncoding()));
                } else {
                    json.append(new String(buffer, 0, lidos, "UTF-8"));
                }
            }
            responseStream.close();
            return json.toString().trim();
        } catch (Exception ex) {
            LOG.error( ex.getMessage(), ex );
            throw new RuntimeException( ex.getMessage(), ex );
        } finally {
            conn.disconnect();
        }
    }
}