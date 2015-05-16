package ip.rest;

import org.apache.commons.codec.binary.Base64;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class OBDDataRestfulClient {

    private final String baseUrl = "http://iot.tt.com.pl:8080/Thingworx/Things/OBDDataRestful/Services/queryStreamEntriesByOBDThingName?";
    private final String username = "ExtensionAdmin";
    private final String password = "extadmin";
    private final String OBDThingName = "OBDData356363050500752";

    public String getRESTResponse(long startDate, long endDate) {
        return getDataFromServer(startDate, endDate);
    }

    String getDataFromServer(long startDate, long endDate) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(baseUrl);
            HttpURLConnection urlConnection = setUsernamePassword(url);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setDoOutput(true);

            sb.append("OBDThingName=").append(OBDThingName).append("&startDate=").append(startDate).append("&endDate=").append(endDate);
            String urlParameters = sb.toString();
            sb.setLength(0);
            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);           

            try (DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream())) {
                wr.write(postData);
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();

            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private HttpURLConnection setUsernamePassword(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        String authString = username + ":" + password;
        String authStringEnc = new String(Base64.encodeBase64(authString.getBytes()));
        urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
        return urlConnection;
    }
}
