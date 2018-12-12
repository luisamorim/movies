package amorim.com.br.movies;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import amorim.com.br.movies.utils.NetworkUtils;

public class MainActivity extends AppCompatActivity {

    TextView mMoviesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMoviesTextView = (TextView) findViewById(R.id.tv_movies);

        new FetchMoviewAsynTask().execute("");

    }

    public class FetchMoviewAsynTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            try{
                String result = NetworkUtils.getResponseFromHttpUrlConnection();
                return result;
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if(s != null && !s.equals("")){
                mMoviesTextView.setText(s);
            }
        }
    }
}
