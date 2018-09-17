package br.com.vetfind.vet_find_app;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;

public class SplashScreenActivity extends Activity {

    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mp = MediaPlayer.create(SplashScreenActivity.this , R.raw.latido);
        mp.start();

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intentVaiPrologin = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intentVaiPrologin);
                finish();
                mp.stop();
            }
        }, 2500);
    }
}
