package com.vengalrao.android.androidjokedisplaylib;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    private static final String KEY="JOKE";
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        Intent intent=getIntent();
        String joke=intent.getStringExtra(KEY);
        mTextView = (TextView) findViewById(R.id.joke_text_view);
        mTextView.setText(joke);
    }
}
