package com.antoinerenou.findintruder;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;

import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Random;

public class AnimationActivity extends Activity implements View.OnTouchListener{

    public int entities;
    public int color;
    public int speed;
    private ShapeDrawable mDrawable;
    int ScreenWidth;
    int ScreenHeight;
    AnimationView animV;
    Bitmap[] bm;
    int intru;
    long startTime,stopTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        setExtrasSettings();
        startTime = System.currentTimeMillis();
        Display display = getWindowManager().getDefaultDisplay();
        Point sizeProx = new Point();
        display.getSize(sizeProx);
        ScreenWidth = sizeProx.x;
        ScreenHeight = sizeProx.y;

        Random rand = new Random();
        intru = rand.nextInt(entities);

        bm = new Bitmap[entities];
        for(int j=0; j<bm.length;j++){
            bm[j] = BitmapFactory.decodeResource(getResources(), R.drawable.glassicon);
        }


        LinearLayout Container = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_animation, null);
        animV = new AnimationView(getApplicationContext(),bm,speed,color,intru);
        Container.addView(animV);
        animV.setOnTouchListener(this);
        setContentView(Container);


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if(animV.pos[intru][0]-100<event.getX() && event.getX()<animV.pos[intru][0]+100 &&
                animV.pos[intru][1]-100<event.getY() && event.getY()<animV.pos[intru][1]+100) {
            stopTime = System.currentTimeMillis();
            long score = stopTime-startTime;
            setWinActivity(score);
        } else {
            failureEvent();
        }
        return false;
    }

    protected void setWinActivity(long score){
        Intent i = new Intent(this, WinActivity.class);
        i.putExtra("score", score);
        startActivity(i);
    }

    protected void failureEvent(){
        System.out.println("_____________________FAIL__________________");
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