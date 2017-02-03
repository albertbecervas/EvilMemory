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

import com.crashlytics.android.Crashlytics;
import com.evilmem.albert.evilmemory.Data.LoginHelper;
import com.evilmem.albert.evilmemory.Data.User;
import com.evilmem.albert.evilmemory.R;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;

import static android.support.design.R.styleable.View;

public class Login extends AppCompatActivity implements View.OnClickListener{

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "k35bsp4ge4l2wnbVfi3Ma4ly0";
    private static final String TWITTER_SECRET = "6HlgtkHTaFfTmUlPYu6pc2bxdBrvwbzdg7G0YZNDyxb8y0F2eW";
    private TwitterLoginButton loginButton;


    LoginHelper loginHelper;
    EditText name, password;

    SharedPreferences settings;
    SharedPreferences.Editor editor;

    Boolean UserLoggedIn,keepin, StateIn;

    CheckBox checkKeep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig), new Crashlytics());
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


        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // The TwitterSession is also available through:
                // Twitter.getInstance().core.getSessionManager().getActiveSession()
                TwitterSession session = result.data;
                String msg = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")";
                startActivity(new Intent(getApplicationContext(),drawer.class));
            }
            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });

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

    private void LoginRealmUser() {
        Realm realm = Realm.getDefaultInstance();

        User user = new User();
        user.getName();
        user.getPass();

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(user);
        realm.commitTransaction();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }
}

//TODO Login con twutter peta y no se porque(problema al conectarse con localhost


