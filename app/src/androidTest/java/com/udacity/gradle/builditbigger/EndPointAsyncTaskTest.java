package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.vengalrao.myapplication.backend.jokeApi.JokeApi;
import com.example.vengalrao.myapplication.backend.jokeApi.model.MyBean;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

/**
 * Created by vengalrao on 31-03-2017.
 */
@RunWith(AndroidJUnit4.class)
public class EndPointAsyncTaskTest{

    private static final String LOG_TAG = "NonEmptyStringTest";

    @Test
    public void test(){
        String result = null;
        EndpointsAsyncTask endPointAsyncTask=new EndpointsAsyncTask();
        endPointAsyncTask.execute();
        try {
            result = endPointAsyncTask.get(20, TimeUnit.SECONDS);
            assertThat(result, notNullValue());
            assertTrue(result.length() > 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {
    private JokeApi jokeApi = null;
    @Override
    protected String doInBackground(Context... params) {
        if(jokeApi == null) {  // Only do this once
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8008/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            jokeApi = builder.build();
        }

        try {
            String j=jokeApi.setJoke(new MyBean()).execute().getData();

            return j;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {

    }
}
