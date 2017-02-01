package com.evilmem.albert.evilmemory.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.evilmem.albert.evilmemory.R;

import static android.R.attr.data;

public class BoundService extends Service {
    public int codigo = 0;
    private IBinder binder = new MyBinder();
    MediaPlayer mediaPlayer;
    Uri songURI;
    public String s;

    public class MyBinder extends Binder{
        public BoundService getService() {
            return BoundService.this;
        }
    }

    @Override
    public IBinder onBind(Intent arg0) {

        mediaPlayer = MediaPlayer.create(this,R.raw.starwars);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        });

        songURI = Uri.parse("android.resource://com.evilmem.albert.evilmemory/raw/starwars");

        try {
          //  String s= getPackageName();
            //Log.d("package", "onCreate: "+ s);
            //mediaPlayer.setDataSource(getApplicationContext(), R.raw.starwars);
//            mediaPlayer.prepare();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return binder;
    }

    @Override
    public void onDestroy() {
        //TODO código para liberar recursos
    }

    public void playSong(){
            try {
                int pos;
                s = songURI.toString();
                s=s.trim();
                pos = s.lastIndexOf("/");
                if(pos!=-1){
                    s= s.substring(pos+1);
                }
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
                Log.d("uri", "playSong: "+s);
                mediaPlayer.start();

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }


    public void pauseSong(){

        try {
            mediaPlayer.pause();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void stopSong(){
        try {
            mediaPlayer.stop();
            mediaPlayer.prepare();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void playSongFromMemory(Intent data){

        try {
            mediaPlayer.stop();
            mediaPlayer.reset();
            /* Con la canción ya cargada, tenemos que reproducir la canción */
            mediaPlayer.setDataSource(this,data.getData());
            mediaPlayer.prepare();
            mediaPlayer.start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playNext(){
        try {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.setDataSource(this,Uri.parse("android.resource://com.evilmem.albert.evilmemory/raw/madness"));
            mediaPlayer.prepare();
            mediaPlayer.start();
            int pos;
            songURI = Uri.parse("android.resource://com.evilmem.albert.evilmemory/raw/madness");
            s = songURI.toString();
            s=s.trim();
            pos = s.lastIndexOf("/");
            if(pos!=-1){
                s= s.substring(pos+1);
            }
            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
            Log.d("uri", "playSong: "+s);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void on_destroy(){
        mediaPlayer.release();
    }






}
