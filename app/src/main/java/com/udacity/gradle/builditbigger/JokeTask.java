package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;

import com.example.elshaarawy.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by elshaarawy on 17-May-17.
 */

public class JokeTask extends AsyncTask<Void,Void,String> {

    private static MyApi myApiService = null;
    private boolean isSuccess;
    private Context context;
    private JokeTaskListener mJokeTaskListener;

    public JokeTask(Context context, JokeTaskListener mJokeTaskListener) {
        this.context = context;
        this.mJokeTaskListener = mJokeTaskListener;
    }

    @Override
    protected String doInBackground(Void... params) {

        if (myApiService==null){
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("https://build-it-bigger-167906.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }

        try {
            isSuccess = true;
            return myApiService.tellJoke().execute().getData();
        }catch (Exception e){
            isSuccess = false;
            return null;
        }

    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mJokeTaskListener.result(isSuccess,s);
        MainActivity.sendMeBroadCast(context,isSuccess,s);
    }
}
