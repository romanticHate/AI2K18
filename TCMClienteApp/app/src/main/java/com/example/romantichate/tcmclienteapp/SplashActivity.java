package com.example.romantichate.tcmclienteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    private TextView textViewTextoLogo;
    private ImageView imageViewLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        textViewTextoLogo= (TextView) findViewById(R.id.textViewTextoLogo);
        imageViewLogo= (ImageView) findViewById(R.id.imageViewLogo);
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.transicion_alpha);

        textViewTextoLogo.startAnimation(anim);
        imageViewLogo.startAnimation(anim);

        final Intent i = new Intent(this,MainActivity.class);

        Thread timer =  new Thread(){
            public void run(){

                try{
                    sleep(5000);
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
