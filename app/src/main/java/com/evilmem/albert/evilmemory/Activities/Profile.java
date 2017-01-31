package com.evilmem.albert.evilmemory.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.evilmem.albert.evilmemory.Data.LoginHelper;
import com.evilmem.albert.evilmemory.R;
import com.evilmem.albert.evilmemory.Activities.drawer;

import static android.R.attr.finishOnCloseSystemDialogs;
import static android.R.attr.id;

public class Profile extends drawer{

    Button Edit,Logout;

    SharedPreferences settings;
    SharedPreferences.Editor editor;

    TextView name, uname, address, maxscore4, maxscore6,maxscore8;

    LoginHelper loginHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Edit = (Button) findViewById(R.id.edit_profile);
        Logout = (Button) findViewById(R.id.log_out);

        Edit.setOnClickListener(listener);
        Logout.setOnClickListener(listener);

        settings= getSharedPreferences("myApp", Context.MODE_PRIVATE);
        editor= settings.edit();

        loginHelper = new LoginHelper(getApplicationContext());

        String username = settings.getString("username", "error");

        Cursor c = loginHelper.getUserData(username);
        String completename = null;
        String location= null;
        String score4=null;
        String score6=null;
        String score8=null;


        if(c.moveToFirst()){
            completename = c.getString(c.getColumnIndex("completename"));
            location = c.getString(c.getColumnIndex("address"));
            score4 = c.getString(c.getColumnIndex("score4"));
            score6 = c.getString(c.getColumnIndex("score6"));
            score8 = c.getString(c.getColumnIndex("score8"));


        }


        name=(TextView)findViewById(R.id.name);
        name.setText(completename);

        uname=(TextView) findViewById(R.id.username);
        uname.setText(username);

        address=(TextView) findViewById(R.id.address);
        address.setText(location);

        maxscore4=(TextView) findViewById(R.id.best4x4);
        maxscore4.setText(score4);

        maxscore6=(TextView) findViewById(R.id.best6x6);
        maxscore6.setText(score6);

        maxscore8=(TextView) findViewById(R.id.best8x8);
        maxscore8.setText(score8);





    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.edit_profile:
                    Intent profileIntent = new Intent(getApplicationContext(),EditProfile.class);
                    startActivity(profileIntent);
                    break;
                case R.id.log_out:

                    View.OnClickListener myOnClickListener = new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            editor.putBoolean("UserLoggedIn", false);
                            editor.putBoolean("keepin", false);
                            editor.apply();
                            Intent i = new Intent(getApplicationContext(),Login.class);
                            startActivity(i);
                        }
                    };

                    Snackbar.make(v, R.string.snackbar_text, Snackbar.LENGTH_LONG)
                            .setAction(R.string.snackbar_action, myOnClickListener)
                            .setActionTextColor(Color.RED)
                            .show(); // Importante!!! No olvidar mostrar la Snackbar.
                    break;


            }

        }

    };
}
