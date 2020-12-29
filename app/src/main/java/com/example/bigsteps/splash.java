package com.example.bigsteps;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

public class splash extends Activity {
    Handler handler;
    MediaPlayer ourSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ourSound = MediaPlayer.create(this, R.raw.splash);
        ourSound.start();

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 6000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ourSound.release();
        finish();
    }

}
