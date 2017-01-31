package com.evilmem.albert.evilmemory.Activities;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.evilmem.albert.evilmemory.R;
import com.example.material.joanbarroso.flipper.CoolImageFlipper;

public class Flipper extends AppCompatActivity {

    Drawable flipper, burger;
    Boolean isBurger = true;

    CoolImageFlipper fliper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flipper);

        fliper = new CoolImageFlipper(this);

        flipper = getResources().getDrawable(R.drawable.ic_evil);
        burger = getResources().getDrawable(R.drawable.ic_angel);
    }


    public void flip(View view) {
        if(isBurger){
            fliper.flipImage(flipper, ((ImageView) view));

        }else{
            fliper.flipImage(burger, ((ImageView) view));
        }
        isBurger= !isBurger;

    }
}
