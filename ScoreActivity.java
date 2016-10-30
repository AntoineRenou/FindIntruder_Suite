package com.antoinerenou.findintruder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by AR on 18/10/2016.
 */
public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
    }
    public void displayHomeActivity(View view){
        finish();
    }
    @Override
    public void onBackPressed() {
        //DO NOTHING
    }
}
