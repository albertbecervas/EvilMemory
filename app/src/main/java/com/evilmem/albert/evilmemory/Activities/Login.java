package com.evilmem.albert.evilmemory.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import com.evilmem.albert.evilmemory.Data.LoginHelper;
import com.evilmem.albert.evilmemory.R;

import static android.support.design.R.styleable.View;

public class Login extends AppCompatActivity implements View.OnClickListener{

    LoginHelper loginHelper;
    EditText name, password;

    SharedPreferences settings;
    SharedPreferences.Editor editor;

    Boolean UserLoggedIn,keepin, StateIn;

    CheckBox checkKeep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        checkKeep = (CheckBox) findViewById(R.id.checkBox);
        checkKeep.setOnClickListener(this);

        //Para consultar Shared Preferences
        settings= getSharedPreferences("myApp", Context.MODE_PRIVATE);
        checkLogin();
        editor= settings.edit();

        loginHelper = new LoginHelper(getApplicationContext());
        name = (EditText)findViewById(R.id.user);
        password =  (EditText) findViewById(R.id.password);
    }

    public void checkLogin(){
        UserLoggedIn = settings.getBoolean("UserLoggedIn", false);
        StateIn = settings.getBoolean("keepin", false);


        if(UserLoggedIn && StateIn){
            Intent intent = new Intent(this,EvilMemory.class);
            startActivity(intent);
        }
    }

    public void Login(View v){
        Cursor cursor= loginHelper.getUserPassName(String.valueOf(name.getText().toString()));
        String enteredPassword = String.valueOf(password.getText().toString());
        String dbPassword = null;
        Boolean emptyFields=false;

        if(cursor.moveToFirst()){
            dbPassword = cursor.getString(cursor.getColumnIndex("password"));
        }

        if(name.getText().toString().matches("")) {
            name.setError("Empty username");
            emptyFields=true;
        }
        if(password.getText().toString().matches("")){
            password.setError("Empty password");
            emptyFields=true;
        }

        if(!emptyFields){
            if(enteredPassword.equals(dbPassword)){
                editor.putBoolean("UserLoggedIn", true);
                editor.putString("username",name.getText().toString());
                editor.apply();
                Intent intent = new Intent(getApplicationContext(), EvilMemory.class);
                startActivity(intent);
            }else{
                Intent i= new Intent(getApplicationContext(), ImgLogErr.class);
                startActivity(i);
            }
        }
    }


    public void goToSignin(View v){

        Intent intent = new Intent(getApplicationContext(), Signin.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.checkBox:
                if(checkKeep.isChecked()){
                    Toast.makeText(getApplicationContext(),"in", Toast.LENGTH_SHORT).show();
                    keepin=true;
                    editor.putBoolean("keepin", true);
                }else{
                    Toast.makeText(getApplicationContext(),"out", Toast.LENGTH_SHORT).show();
                    keepin=false;
                    editor.putBoolean("keepin",false);
                }
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }
}


