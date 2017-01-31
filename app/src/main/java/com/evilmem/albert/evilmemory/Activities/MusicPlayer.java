package com.evilmem.albert.evilmemory.Activities;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.evilmem.albert.evilmemory.R;
import com.evilmem.albert.evilmemory.Activities.drawer;

import java.io.IOException;

import static android.media.AudioManager.STREAM_MUSIC;

public class MusicPlayer extends drawer {

    MediaPlayer mediaPlayer;
    ImageView play;
    Button selectSong;
    SeekBar volume;
    AudioManager audioManager;
    Boolean set;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        play = (ImageView) findViewById(R.id.imageButton);
        play.setOnClickListener(listener);

        selectSong = (Button) findViewById(R.id.selectSong);
        selectSong.setOnClickListener(listener);

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        });

        try {
            String s= getPackageName();
            Log.d("package", "onCreate: "+ s);
            mediaPlayer.setDataSource(getApplicationContext(), Uri.parse("android.resource://com.evilmem.albert.evilmemory/raw/starwars"));
            mediaPlayer.prepare();
            mediaPlayer.getTrackInfo();
            set=true;

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        play.setImageResource(R.drawable.ic_playstation_logo);

        Volume();

    }


    private void Volume() {

        try {
            volume = (SeekBar)findViewById(R.id.seekBar);
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            volume.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            volume.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

            volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override            public void onStopTrackingTouch(SeekBar arg0) {
                }

                @Override            public void onStartTrackingTouch(SeekBar arg0) {
                }

                @Override            public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.imageButton:
                        if(!set){
                            try {
                                play.setImageResource(R.drawable.ic_playstation_logo);
                                play.invalidate();
                                mediaPlayer.pause();
                                set=true;
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }else{
                            try {
                                play.setImageResource(R.drawable.ic_stop);
                                mediaPlayer.start();
                                set=false;
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    break;
                case R.id.selectSong:
                    Uri myUri = Uri.parse("android.resource://res/raw");
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    try {
                        mediaPlayer.setDataSource(getApplicationContext(), myUri);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;


            }

        }

    };


        @Override
    protected void onDestroy() {
        mediaPlayer.stop();
        super.onDestroy();
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                try {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(this,data.getData());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }*/
}
