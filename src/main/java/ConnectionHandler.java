import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionHandler {
    private String jsonString;
    private static final String USER_AGENT = "Mozilla/5.0";

    public static String getMyUrl() {
        return MY_URL;
    }

    private static String MY_URL;

    public ConnectionHandler(){
        this.MY_URL = "www.google.ca";
    }

    public ConnectionHandler(String URL){
        this.MY_URL = URL;
    }

    //Send Get
    public String sendGET() throws IOException {
        java.net.URL obj = new URL(getMyUrl());
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;

            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //Returns the response
            if(MagicNumbers.DEBUG)
                System.out.println(response.toString());
            return response.toString();
        } else {
            if(MagicNumbers.DEBUG)
                System.out.println("GET request not worked");
            return null;
        }
    }//End of GET
}
