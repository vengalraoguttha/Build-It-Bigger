package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Pair;
import android.view.View;
import android.widget.ProgressBar;

import com.example.vengalrao.myapplication.backend.jokeApi.JokeApi;
import com.example.vengalrao.myapplication.backend.jokeApi.model.MyBean;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.vengalrao.android.androidjokedisplaylib.JokeActivity;

import java.io.IOException;

/**
 * Created by vengalrao on 30-03-2017.
 */

public class EndPointAsyncTask extends AsyncTask<Pair<Context,String>,Void,String> {

    private JokeApi jokeApi;
    private Context context;
    private static final String KEY="JOKE";
    private ProgressBar mProgressBar;

    public EndPointAsyncTask(Context c ,ProgressBar progressBar){
        context=c;
        mProgressBar=progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(mProgressBar!=null){
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if(jokeApi==null){
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            jokeApi=builder.build();
        }

        try {
            String j=jokeApi.setJoke(new MyBean()).execute().getData();

            return j;

        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if(mProgressBar!=null){
            mProgressBar.setVisibility(View.GONE);
        }
        Intent intent=new Intent(context, JokeActivity.class);
        intent.putExtra(KEY,result);
        context.startActivity(intent);
    }
}
