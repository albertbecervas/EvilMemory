package com.evilmem.albert.evilmemory.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.evilmem.albert.evilmemory.R;

public class ImgLogErr extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_log_err);
    }

    public void backLogin(View v){
        Intent i = new Intent(getApplicationContext(),Login.class);
        startActivity(i);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }
}
