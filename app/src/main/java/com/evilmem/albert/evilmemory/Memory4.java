package com.evilmem.albert.evilmemory;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.material.joanbarroso.flipper.CoolImageFlipper;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindDrawable;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Memory4 extends AppCompatActivity implements View.OnClickListener{

    ImageView i1,i2,i3,i4,i5,i6,i7,i8,i9,i10,i11,i12,i13,i14,i15,i16;
    Drawable d1,d2,d3,d4,d5,d6,d7,d8;

    /*@BindViews({ R.drawable.ic_angel, R.drawable.ic_angel_and_demon_, R.drawable.ic_call_black_24dp, R.drawable.ic_evil,
            R.drawable.ic_camera_enhance_black_24dp,R.drawable.ic_dialpad_black_24dp,R.drawable.ic_exit_to_app_black_24dp,
            R.drawable.ic_explore_black_24dp })
    List<View> drawables;*/

    Drawable[] drawables = new Drawable[16];

    int[] vector;


    Boolean isVisible = true;
    Boolean isFirst = true;

    CoolImageFlipper flipper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_memory4);

        flipper = new CoolImageFlipper(this);

        setCards();

    }

    public void setCards() {

        drawables[0] = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_angel);
        drawables[1] = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_evil);
        drawables[2] = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_angel_and_demon_);
        drawables[3] = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_call_black_24dp);
        drawables[4] = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_camera_enhance_black_24dp);
        drawables[5] = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_dialpad_black_24dp);
        drawables[6] = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_exit_to_app_black_24dp);
        drawables[7] = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_explore_black_24dp);
        drawables[8] = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_angel);
        drawables[9] = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_evil);
        drawables[10] = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_angel_and_demon_);
        drawables[11] = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_call_black_24dp);
        drawables[12] = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_camera_enhance_black_24dp);
        drawables[13] = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_dialpad_black_24dp);
        drawables[14] = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_exit_to_app_black_24dp);
        drawables[15] = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_explore_black_24dp);
        //Collections.shuffle(drawables);
    }

    public void flipper(View view){
        if(isVisible){
            flipper.flipImage(d1, ((ImageView) view));

        }else{
            flipper.flipImage(d2, ((ImageView) view));
        }
        isVisible= !isVisible;
    }

    public void action(View view){
        if(!isVisible){
            if(isFirst){
                flipper(view);
                isFirst=false;
            }else{
                //flipper(view);
                /*
                if(matches){
                    dontFlip();
                    progressBar+=1;
                }else{
                    flipper de les dues
                    intents++;
                }*/


            }
        }

    }


    @OnClick({R.id.imageView0,R.id.imageView1,R.id.imageView2,R.id.imageView3,R.id.imageView8,R.id.imageView9,R.id.imageView10,R.id.imageView11
    ,R.id.imageView16,R.id.imageView17,R.id.imageView18,R.id.imageView19,R.id.imageView24,R.id.imageView25,R.id.imageView26,R.id.imageView27})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.imageView0:
                int i = 0;
                action(view);
                flipper(view);
                break;

            case R.id.imageView1:
                action(view);
                break;

            case R.id.imageView2:
                action(view);
                break;

            case R.id.imageView3:
                action(view);
                break;

            case R.id.imageView8:
                action(view);
                break;

            case R.id.imageView9:
                action(view);
                break;

            case R.id.imageView10:
                action(view);
                break;

            case R.id.imageView11:
                action(view);
                break;

            case R.id.imageView16:
                action(view);
                break;

            case R.id.imageView17:
                action(view);
                break;

            case R.id.imageView18:
                action(view);
                break;

            case R.id.imageView19:
                action(view);
                break;

            case R.id.imageView24:
                action(view);
                break;

            case R.id.imageView25:
                action(view);
                break;

            case R.id.imageView26:
                action(view);
                break;

            case R.id.imageView27:
                action(view);
                break;
        }
    }

    public void setRandomGrid(){
        Random rand = new Random();
        int a,b,aux;
        for(int i = 0;i<32;i++){

           // a = rand.nextInt(random.length+1);
           // b= rand.nextInt(random.length+1);


        }

    }
}
