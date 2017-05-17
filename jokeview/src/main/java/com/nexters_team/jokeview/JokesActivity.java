package com.nexters_team.jokeview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static com.nexters_team.jokeview.R.id.joke_textView;

public class JokesActivity extends AppCompatActivity {

    private static final String JOKE_EXTRA = "joke_extra";
    TextView mJokeBodyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);
        mJokeBodyTextView = (TextView) findViewById(joke_textView);

        String joke = getIntent().getStringExtra(JOKE_EXTRA);
        mJokeBodyTextView.setText(joke);

    }

    public static void startMe(Context context,String joke){
        Intent intent = new Intent(context,JokesActivity.class);
        intent.putExtra(JOKE_EXTRA,joke);
        context.startActivity(intent);
    }

}
