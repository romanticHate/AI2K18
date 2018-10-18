package com.example.romantichate.tcmandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    private TextView tv;
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tv= (TextView) findViewById(R.id.tv);
        iv= (ImageView) findViewById(R.id.iv);
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.splashtransition);
        tv.startAnimation(anim);
        iv.startAnimation(anim);
        final Intent i = new Intent(this,MainActivity.class);
        Thread timer =  new Thread(){
            public void run(){

                try{
                    sleep(4500);
                }catch(InterruptedException ie){
                    ie.printStackTrace();
                }finally{
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }
}
