package com.mohit.musicx;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer musicPlayer = new MediaPlayer();

    public void play(View view) {
        //playing the music
        musicPlayer.start();
    }

    public void pause(View view) {
        //pausing the music
        musicPlayer.pause();
    }

    public void stop(View view) {
        //stoping the music
        musicPlayer.stop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final AudioManager audioManager;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        musicPlayer=MediaPlayer.create(this, R.raw.music);

        audioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
        int maxVol= audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currVol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        SeekBar seekVol = findViewById(R.id.seekVol);
        seekVol.setMax(maxVol);
        seekVol.setProgress(currVol);
        seekVol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final SeekBar seekProg = findViewById(R.id.seekBar);
        seekProg.setMax(musicPlayer.getDuration());

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekProg.setProgress(musicPlayer.getCurrentPosition());
            }
        }, 0,900);

        seekProg.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                musicPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
