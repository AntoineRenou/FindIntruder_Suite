package com.antoinerenou.findintruder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public int entities = 2;
    public int color = -1;
    public int speed = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void setExtrasSettings(Intent settingsIntent){
        entities = settingsIntent.getExtras().getInt("entities");
        color = settingsIntent.getExtras().getInt("color");
        speed = settingsIntent.getExtras().getInt("speed");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        setExtrasSettings(data);
    }

    public void displayPlayActivity(View view){
        Intent i = new Intent(this, AnimationActivity.class);
        i.putExtra("entities", entities);
        i.putExtra("color", color);
        i.putExtra("speed", speed);
        startActivity(i);
    }

    public void displaySettingsActivity(View view){
        Intent i = new Intent(this, SettingsActivity.class);
        startActivityForResult(i,1000);
    }


    public void displayRulesActivity(View view){
        Intent i = new Intent(this, RulesActivity.class);
        startActivity(i);
    }

    public void displayScoreActivity(View view){
        Intent i = new Intent(this, ScoreActivity.class);
        startActivity(i);
    }


}
