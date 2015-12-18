package matigian.pruebajason2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by italiano Leo on 18-12-2015.
 */
public class HttpConnection {

    public String connectToServer(String myURL, int timeOut){

        try {
            URL url = new URL(myURL);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();

            conexion.setConnectTimeout(timeOut);
            conexion.setRequestMethod("GET");
            conexion.setDoInput(true);

            conexion.connect();

            InputStream is = conexion.getInputStream();

            return readInputStream(is);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return  null;

        }

    }

    private String readInputStream(InputStream stream) throws IOException{
        Reader reader = null;
        StringBuilder inputStringBuilder = new StringBuilder();

        reader = new InputStreamReader(stream, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
        while (line!= null){
            inputStringBuilder.append(line);
            inputStringBuilder.append('\n');
            line = bufferedReader.readLine();
        }
        return inputStringBuilder.toString();
    }
}

