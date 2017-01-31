package com.evilmem.albert.evilmemory.Activities;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.evilmem.albert.evilmemory.Data.LoginHelper;
import com.evilmem.albert.evilmemory.R;

import java.io.IOException;

public class Signin extends AppCompatActivity implements View.OnClickListener{

    EditText name,password,Completename,address;
    LoginHelper loginHelper;

    ImageView profile;
    Uri selectedImage;

    FloatingActionButton fab;

    Boolean Robot;

    CheckBox robot;

    SharedPreferences settings;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        name = (EditText)findViewById(R.id.user);
        password =  (EditText) findViewById(R.id.password);
        profile = (ImageView) findViewById(R.id.imageView2);

        fab= (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        Completename = (EditText) findViewById(R.id.completeuser);

        robot = (CheckBox) findViewById(R.id.robot);
        robot.setOnClickListener(this);

        Robot=true;

        settings= getSharedPreferences("myApp", Context.MODE_PRIVATE);
        editor = settings.edit();

        loginHelper = new LoginHelper(getApplicationContext());
    }


    public void Signin(View v){
        if(!Robot) {
            ContentValues valuesToStore = new ContentValues();
            valuesToStore.put("name", String.valueOf(name.getText()));
            valuesToStore.put("password", String.valueOf(password.getText()));
            valuesToStore.put("completename", String.valueOf(Completename.getText()));
            editor.putBoolean("UserLoggedIn", true);
            editor.putString("username",name.getText().toString());
            editor.apply();

            loginHelper.createUser(valuesToStore, "Users");

            Toast.makeText(getApplicationContext(), "Welcome " + name.getText(), Toast.LENGTH_LONG).show();
            name.setText("");
            password.setText("");

            Intent intent = new Intent(getApplicationContext(), EvilMemory.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(), "get the fuck out MrRobot!", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                Intent changeImage = new Intent(Intent.ACTION_GET_CONTENT, null);
                changeImage.setType("image/*");
                startActivityForResult(changeImage,1);
                break;
            case R.id.robot:
                if(robot.isChecked()){
                    Robot=false;
                }else{
                    Robot=true;
                }
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Como en este caso los 3 intents hacen lo mismo, si el estado es correcto recogemos el resultado
        //Aún así comprobamos los request code. Hay que tener total control de lo que hace nuestra app.
        if(resultCode == RESULT_OK){
            if(requestCode == 1){
                data.getData();
                selectedImage = data.getData();
                Log.v("PICK","Selected image uri" + selectedImage);
                try {
                    profile.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{
            Log.v("Result","Something happened");
        }
    }
}

//TODO insertar imagen de perfil
