package com.sarahehabm.builditbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.sarahehabm.jokeapp.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by Sarah E. Mostafa on 20-Feb-16.
 */
public class JokesAsyncTask extends AsyncTask<JokeRetrievedCallback, Void, String>{
    private static MyApi myApi;
    private JokeRetrievedCallback jokeRetrievedCallback;

    public JokesAsyncTask(JokeRetrievedCallback jokeRetrievedCallback) {
        this.jokeRetrievedCallback = jokeRetrievedCallback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        jokeRetrievedCallback.showProgressBar();
    }

    @Override
    protected String doInBackground(JokeRetrievedCallback... params) {
        if(myApi == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("https://joketeller2-1227.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            myApi = builder.build();
        }

        jokeRetrievedCallback = params[0];

        try {
            return myApi.sayHi("Sarah").execute().getData();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        jokeRetrievedCallback.hideProgressBAr();
        jokeRetrievedCallback.onJokeRetrieved(s);
    }
}
