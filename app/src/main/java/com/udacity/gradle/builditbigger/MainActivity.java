package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.Joker;
import com.nexters_team.jokeview.JokesActivity;


public class MainActivity extends AppCompatActivity {
    private static final String MAIN_ACTIVITY_ACTION = "net.elshaarawy.MainActivity";
    private static final String EXTRA_SUCCESS = "extra_success";
    private static final String EXTRA_DATA = "extra_data";

    private Joker mJoker;

    private LocalBroadcastManager mLocalBroadcastManager;

    ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mJoker = Joker.getInstance();

        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Loading Joke ... ");
    }


    private BroadcastReceiver mRefreshingReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (MAIN_ACTIVITY_ACTION.equals(intent.getAction())) {
                mProgressDialog.hide();
                boolean isSuccess = intent.getBooleanExtra(EXTRA_SUCCESS, false);
                String data = intent.getStringExtra(EXTRA_DATA);
                if (isSuccess){
                    JokesActivity.startMe(MainActivity.this,data);
                }
                else {
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_LONG).show();
                }
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        mLocalBroadcastManager.registerReceiver(mRefreshingReceiver,
                new IntentFilter(MAIN_ACTIVITY_ACTION));
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLocalBroadcastManager.unregisterReceiver(mRefreshingReceiver);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
//        Toast.makeText(this, mJoker.tellJoke(), Toast.LENGTH_LONG).show();

//        JokesActivity.startMe(this,mJoker.tellJoke());
        new JokeTask(this, new JokeTaskListener() {
            @Override
            public void result(boolean isSuccess, String Data) {

            }
        }).execute();
        mProgressDialog.show();
    }

    public static void sendMeBroadCast(Context context, boolean isSuccess,String data){
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(context);
        Intent intent = new Intent(MAIN_ACTIVITY_ACTION);
        intent.putExtra(EXTRA_SUCCESS,isSuccess);
        intent.putExtra(EXTRA_DATA,data);
        broadcastManager.sendBroadcast(intent);
    }

}
