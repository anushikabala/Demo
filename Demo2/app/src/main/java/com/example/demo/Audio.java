package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class Audio extends AppCompatActivity {
    private SoundPool soundPool;
    private int s1,s2,s3,s4,s5;
    private int sa,sb,sc,sd,se;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes=new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(5)
                    .setAudioAttributes(audioAttributes)
                    .build();
        }else{
            soundPool=new SoundPool(5, AudioManager.STREAM_MUSIC,0);
        }

        s1 = soundPool.load(this,R.raw.dilkyunyeh,1);
        s2 = soundPool.load(this,R.raw.ilahi,1);
        s3 = soundPool.load(this,R.raw.mainagarkahoon,1);
        s4 = soundPool.load(this,R.raw.merebina,1);
        s5 = soundPool.load(this,R.raw.phirseudchala,1);
    }
    public void playSound(View v){
        switch ((v.getId())){
            case R.id.btn_s1:
                sa= soundPool.play(s1,1,1,0,0,1);
                soundPool.pause(sb);
                soundPool.pause(sc);
                soundPool.pause(sd);
                soundPool.pause(se);
                break;
            case R.id.btn_s2:
                sb = soundPool.play(s2,1,1,0,0,1);
                soundPool.pause(sa);
                soundPool.pause(sc);
                soundPool.pause(sd);
                soundPool.pause(se);
                break;
            case R.id.btn_s3:
                sc= soundPool.play(s3,1,1,0,0,1);
                soundPool.pause(sb);
                soundPool.pause(sa);
                soundPool.pause(sd);
                soundPool.pause(se);
                break;
            case R.id.btn_s4:
                sd = soundPool.play(s4,1,1,0,0,1);
                soundPool.pause(sb);
                soundPool.pause(sc);
                soundPool.pause(sa);
                soundPool.pause(se);
                break;
            case R.id.btn_s5:
                se = soundPool.play(s5,1,1,0,0,1);
                soundPool.pause(sb);
                soundPool.pause(sc);
                soundPool.pause(sd);
                soundPool.pause(sa);
                break;
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }
}