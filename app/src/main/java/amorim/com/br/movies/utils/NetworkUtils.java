package amorim.com.br.movies.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    private static final String BASE_URL = "http://api.themoviedb.org/3/movie/popular";

    private static final String API_KEY_PARAM = "api_key";
    private static final String API_KEY = "";

    private static URL defaultURLConnection(){

        Uri.Builder buildUri = Uri.parse(BASE_URL).buildUpon();
        buildUri.appendQueryParameter(API_KEY_PARAM, API_KEY).build();


        URL url = null;
        try{
            url = new URL(buildUri.toString());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }

        return url;

    }

    public static String getResponseFromHttpUrlConnection() throws IOException{

        URL url = defaultURLConnection();

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasNext = scanner.hasNext();

            if(hasNext){
                return scanner.next();
            }else{
                return null;
            }

        }finally {
            urlConnection.disconnect();
        }
    }

}
