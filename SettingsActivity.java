package com.antoinerenou.findintruder;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    public NumberPicker n;
    public int c = -1;
    public SeekBar s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getComponentResource();
        setNumberPicker();
        setSeekBar();
    }

    @Override
    public void onBackPressed(){
        displayHomeActivity(null);
    }

    protected void getComponentResource(){
        n = (NumberPicker) findViewById(R.id.numberPicker);
        s = (SeekBar) findViewById(R.id.seekBar);
    }

    public void getColorResource(View view){
        TextView t = (TextView) findViewById(view.getId());
        Drawable cd = t.getBackground();
        c = ((ColorDrawable) cd).getColor();
    }
    protected void setNumberPicker(){
        n.setMinValue(2);
        n.setMaxValue(10);
    }

    protected void setSeekBar(){
        s.setMax(20);
    }

    public void displayHomeActivity(View view){
        Intent i = new Intent(this, MainActivity.class);
        if(view!=null){
            i.putExtra("entities", n.getValue());
            i.putExtra("color", c);
            i.putExtra("speed", s.getProgress()+1);
        }
        else{
            i.putExtra("entities", 2);
            i.putExtra("color", -1);
            i.putExtra("speed", 1);
        }
        setResult(1000, i);
        finish();
    }
}
