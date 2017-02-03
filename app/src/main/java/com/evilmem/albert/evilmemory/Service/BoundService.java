package com.evilmem.albert.evilmemory.Service;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.evilmem.albert.evilmemory.Activities.EditProfile;
import com.evilmem.albert.evilmemory.R;
import com.evilmem.albert.evilmemory.gps.GPSActivity;

import java.io.IOException;
import java.util.List;

import static android.R.attr.data;
import static android.R.attr.targetActivity;

public class BoundService extends Service {
    private IBinder binder = new MyBinder();
    MediaPlayer mediaPlayer;
    Uri songURI;
    public String s;


    List<Address> addressList;
    LocationManager locationManager;
    LocationListener locationListener;

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


    public void getGPS(){
        //startActivity(new Intent (getApplicationContext(), GPSActivity.class));
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Geocoder geocoder = new Geocoder(getApplicationContext());
                try {
                    addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 3);
                    /*for (int i = 0; i < addressList.size(); ++i) {
                        TextView t = (TextView) findViewById(R.id.address);

                        if (i == 0) t.setText("");
                        t.setText(t.getText() + "\n" + addressList.get(i).getAddressLine(0));
                    }*/
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }

    public void on_destroy(){
        mediaPlayer.release();

    }






}
