package com.evilmem.albert.evilmemory;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.material.joanbarroso.flipper.CoolImageFlipper;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindDrawable;
import butterknife.BindViews;

public class Memory4 extends AppCompatActivity implements View.OnClickListener{

    ImageView i1,i2,i3,i4,i5,i6,i7,i8,i9,i10,i11,i12,i13,i14,i15,i16;
    Drawable d1,d2,d3,d4,d5,d6,d7,d8;

    /*@BindViews({ R.drawable.ic_angel, R.drawable.ic_angel_and_demon_, R.drawable.ic_call_black_24dp, R.drawable.ic_evil,
            R.drawable.ic_camera_enhance_black_24dp,R.drawable.ic_dialpad_black_24dp,R.drawable.ic_exit_to_app_black_24dp,
            R.drawable.ic_explore_black_24dp ,R.drawable.ic_angel, R.drawable.ic_angel_and_demon_, R.drawable.ic_call_black_24dp, R.drawable.ic_evil,
            R.drawable.ic_camera_enhance_black_24dp,R.drawable.ic_dialpad_black_24dp,R.drawable.ic_exit_to_app_black_24dp,
            R.drawable.ic_explore_black_24dp})
    List<Drawable> drawables;*/

    //String img[] = new String[drawables.size()];


    Boolean isVisible = true;
    Boolean isFirst = true;

    CoolImageFlipper flipper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory4);

        flipper = new CoolImageFlipper(this);

        setCards();
    }

    public void setCards() {
        //notVisible
        i1 = (ImageView) findViewById(R.id.imageView0);
        i2 = (ImageView) findViewById(R.id.imageView1);
        i3 = (ImageView) findViewById(R.id.imageView2);
        i4 = (ImageView) findViewById(R.id.imageView3);
        i5 = (ImageView) findViewById(R.id.imageView8);
        i6 = (ImageView) findViewById(R.id.imageView9);
        i7 = (ImageView) findViewById(R.id.imageView10);
        i8 = (ImageView) findViewById(R.id.imageView11);
        i9 = (ImageView) findViewById(R.id.imageView16);
        i10 = (ImageView) findViewById(R.id.imageView17);
        i11 = (ImageView) findViewById(R.id.imageView18);
        i12 = (ImageView) findViewById(R.id.imageView19);
        i13 = (ImageView) findViewById(R.id.imageView24);
        i14 = (ImageView) findViewById(R.id.imageView25);
        i15 = (ImageView) findViewById(R.id.imageView26);
        i16 = (ImageView) findViewById(R.id.imageView27);

        i1.setOnClickListener(this);
        i2.setOnClickListener(this);
        i3.setOnClickListener(this);
        i4.setOnClickListener(this);
        i5.setOnClickListener(this);
        i6.setOnClickListener(this);
        i7.setOnClickListener(this);
        i8.setOnClickListener(this);
        i9.setOnClickListener(this);
        i10.setOnClickListener(this);
        i11.setOnClickListener(this);
        i12.setOnClickListener(this);
        i13.setOnClickListener(this);
        i14.setOnClickListener(this);
        i15.setOnClickListener(this);
        i16.setOnClickListener(this);

        //visible
        //drawables = Arrays.asList();
       // Collections.shuffle(drawables);


      //  for (int i = 0; i++;)



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

    @Override
    public void onClick(View view) {
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
