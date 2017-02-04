package com.evilmem.albert.evilmemory.Activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.location.Address;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.evilmem.albert.evilmemory.Data.LoginHelper;
import com.evilmem.albert.evilmemory.Interface.OnFragmentInteractionListener;
import com.evilmem.albert.evilmemory.R;
import com.evilmem.albert.evilmemory.Service.BoundService;
import com.evilmem.albert.evilmemory.gps.GPSActivity;

import java.io.IOException;
import java.util.List;

import static com.evilmem.albert.evilmemory.R.id.textView;

public class EditProfile extends Activity implements View.OnClickListener, OnFragmentInteractionListener {

    EditText name, address, password;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    LoginHelper loginHelper;

    Button modify;

    FloatingActionButton fab, fabaddress;

    Uri selectedImage;

    ImageView profile;


    BoundService bService;
    boolean bound = false;
    Intent intent;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BoundService.MyBinder binder = (BoundService.MyBinder) iBinder;
            bService = binder.getService();
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            bound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        intent = new Intent(EditProfile.this, BoundService.class);

        bindService(intent, connection, Context.BIND_ADJUST_WITH_ACTIVITY);

        name = (EditText) findViewById(R.id.completeuser);
        address = (EditText) findViewById(R.id.address);
        password = (EditText) findViewById(R.id.password);

        profile = (ImageView) findViewById(R.id.profile_picture);

        sharedPreferences = getSharedPreferences("myApp", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        modify = (Button) findViewById(R.id.modify);
        modify.setOnClickListener(this);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        fabaddress = (FloatingActionButton) findViewById(R.id.fabaddress);
        fabaddress.setOnClickListener(this);

        loginHelper = new LoginHelper(getApplicationContext());

        Context context= getApplicationContext();

        bService.registerReceiver(
                new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        double latitude = intent.getDoubleExtra(bService.EXTRA_LATITUDE, 0);
                        double longitude = intent.getDoubleExtra(bService.EXTRA_LONGITUDE, 0);
                        address.setText("Lat: " + latitude + ", Lng: " + longitude);
                    }
                }, new IntentFilter(bService.ACTION_LOCATION_BROADCAST)
        );

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.modify:
                saveChanges();
                break;
            case R.id.fab:
                Intent changeImage = new Intent(Intent.ACTION_GET_CONTENT, null);
                changeImage.setType("image/*");

                //Este Intent define para el ACTION_PICK, la URI de donde cogerá los datos
                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                //Usamos el Intent anterior para filtrar únicamente los que queremos que usen
                Intent chooserIntent = Intent.createChooser(changeImage, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

                //startActivityForResult(chooserIntent, 1);
                break;
            case R.id.fabaddress:
                Context context= getApplicationContext();
                bService.getGPS(context);
                //startActivity(new Intent(EditProfile.this, GPSActivity.class));
                break;
        }
    }


    public void saveChanges() {
        /*ContentValues valuesToStore = new ContentValues();
        valuesToStore.put("completename", String.valueOf(name.getText()));
        valuesToStore.put("address", String.valueOf(address.getText()));
        valuesToStore.put("password", String.valueOf(password.getText()));*/

        String cname = String.valueOf(name.getText());
        String pass = String.valueOf(password.getText());
        String add = String.valueOf(address.getText());

       /* editor.putBoolean("UserLoggedIn", true);
        editor.putString("username",name.getText().toString());
        editor.apply();*/

        if (cname != "" && cname.length() > 5) {
            loginHelper.modifyName(cname);
        }
        if (pass != "" && pass.length() > 5) {
            loginHelper.modifyPassword(pass);
        }
        if (add != "" && add.length() > 5) {
            loginHelper.modifyAddress(add);
        }

        Toast.makeText(getApplicationContext(), "User settings modified" + name.getText(), Toast.LENGTH_LONG).show();
        //name.setText("");
        //password.setText("");

        Intent intent = new Intent(getApplicationContext(), Profile.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Como en este caso los 3 intents hacen lo mismo, si el estado es correcto recogemos el resultado
        //Aún así comprobamos los request code. Hay que tener total control de lo que hace nuestra app.
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                data.getData();
                selectedImage = data.getData();
                String s = selectedImage.toString();
                Log.d("uri", "onActivityResult: " + s);
                editor.putString("s", s);
                editor.apply();
                try {
                    profile.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Log.v("Result", "Something happened");
        }
    }
    @Override
    public void showMessage(List<Address> addressList) {


        for (int i = 0; i < addressList.size(); ++i) {
            TextView t = (TextView) findViewById(R.id.address);

            if (i == 0) t.setText("");
            t.setText(t.getText() + "\n" + addressList.get(i).getAddressLine(0));
        }
    }
}
