package com.antoinerenou.findintruder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by AR on 18/10/2016.
 */
public class PlayActivity extends AppCompatActivity {
    public int entities;
    public int color;
    public int speed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        setExtrasSettings();

        Intent i = new Intent(this, AnimationActivity.class);
        i.putExtra("entities", entities);
        i.putExtra("color", color);
        i.putExtra("speed", speed);
        startActivity(i);
    }

    protected void setExtrasSettings(){
        Intent i = getIntent();
        entities = i.getExtras().getInt("entities");
        color = i.getExtras().getInt("color");
        speed = i.getExtras().getInt("speed");
    }

    public void displayHomeActivity(View view){
        finish();
    }
}
