package com.evilmem.albert.evilmemory.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.evilmem.albert.evilmemory.R;

public class Troll extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_troll);
    }

    public void back(View v){
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }

    public void onStop(){
        super.onStop();
        this.finish();
    }
}
