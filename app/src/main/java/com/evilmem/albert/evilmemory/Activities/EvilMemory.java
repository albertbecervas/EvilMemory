package com.evilmem.albert.evilmemory.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.evilmem.albert.evilmemory.Memory4;
import com.evilmem.albert.evilmemory.Memory6;
import com.evilmem.albert.evilmemory.Memory8;
import com.evilmem.albert.evilmemory.R;

public class EvilMemory extends drawer implements View.OnClickListener {

    Button easy,normal,hard;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    //String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evil_memory);

        easy = (Button) findViewById(R.id.button5);
        easy.setOnClickListener(this);

        normal = (Button) findViewById(R.id.button6);
        normal.setOnClickListener(this);

        hard = (Button) findViewById(R.id.button7);
        hard.setOnClickListener(this);

        sharedPreferences = getSharedPreferences("myApp", Context.MODE_PRIVATE);
        editor = getSharedPreferences("myApp", 0).edit();

        //username = sharedPreferences.getString("username", "human");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button5:
                startActivity(new Intent(getApplicationContext(), Memory4.class));
                break;
            case R.id.button6:
                startActivity(new Intent(getApplicationContext(), Memory6.class));
                break;
            case R.id.button7:
                startActivity(new Intent(getApplicationContext(), Memory8.class));
                break;
        }
    }
}
