package com.antoinerenou.findintruder;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Random;

public class AnimationActivity extends Activity implements View.OnTouchListener{

    public int entities;
    public byte[] icon;
    public int color;
    public int speed;
    private ShapeDrawable mDrawable;
    int ScreenWidth;
    int ScreenHeight;
    AnimationView animV;
    Bitmap[] bm;
    int intru;
    long startTime,stopTime;
   // public MediaPlayer mpExplo = MediaPlayer.create(this, R.raw.explosion);
//    public MediaPlayer mpShoot = MediaPlayer.create(this, R.raw.shoot);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setParameters();
    }

    @Override
    public void onResume(){
        super.onResume();
        setParameters();

    }

    public void setParameters(){
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
            bm[j] = BitmapFactory.decodeByteArray(icon, 0, icon.length);
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
            final MediaPlayer mpExplo = new MediaPlayer();

            mpExplo.create(this, R.raw.spaceinvaders1);

            mpExplo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    if (mp == mpExplo) {
                        mpExplo.start();
                    }
                }
            });
            stopTime = System.currentTimeMillis();
            long score = stopTime-startTime;
            startTime = 0;
            stopTime = 0;
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
        //mpShoot.start();
        System.out.println("_____________________FAIL__________________");
    }

    protected void setExtrasSettings(){
        Intent i = getIntent();
        entities = i.getExtras().getInt("entities");
        icon = i.getExtras().getByteArray("icon");
        color = i.getExtras().getInt("color");
        speed = i.getExtras().getInt("speed");
    }


    public void displayHomeActivity(View view){
        finish();
    }
    @Override
    public void onBackPressed() {
        //DO NOTHING
    }


}
