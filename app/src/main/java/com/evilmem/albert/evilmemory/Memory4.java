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

    /*@BindViews({ R.drawable.ic_angel, R.drawable.ic_angel_and_demon_, R.drawable.ic_call_black_24dp, R.drawable.ic_evil,
            R.drawable.ic_camera_enhance_black_24dp,R.drawable.ic_dialpad_black_24dp,R.drawable.ic_exit_to_app_black_24dp,
            R.drawable.ic_explore_black_24dp })
    List<View> drawables;*/

    ImageView i1,i2,i3,i4,i5,i6,i7,i8,i9,i10,i11,i12,i13,i14,i15,i16;

    Drawable[] drawables = new Drawable[16];


    boolean isFirst = true;
    boolean[] isVisible= new boolean[16];

    CoolImageFlipper flipper;

    @BindDrawable(R.drawable.ic_fast_rewind_black_24dp) Drawable backside;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ButterKnife.bind(this);
        setContentView(R.layout.activity_memory4);

        flipper = new CoolImageFlipper(this);

        setCards();



    }

    public void setCards() {
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


        drawables[0] = getResources().getDrawable( R.drawable.ic_angel);
        drawables[1] = getResources().getDrawable( R.drawable.ic_evil);
        drawables[2] = getResources().getDrawable( R.drawable.ic_angel_and_demon_);
        drawables[3] = getResources().getDrawable( R.drawable.ic_call_black_24dp);
        drawables[4] = getResources().getDrawable( R.drawable.ic_camera_enhance_black_24dp);
        drawables[5] = getResources().getDrawable(R.drawable.ic_dialpad_black_24dp);
        drawables[6] = getResources().getDrawable(R.drawable.ic_exit_to_app_black_24dp);
        drawables[7] = getResources().getDrawable(R.drawable.ic_explore_black_24dp);
        drawables[8] = getResources().getDrawable( R.drawable.ic_angel);
        drawables[9] = getResources().getDrawable( R.drawable.ic_evil);
        drawables[10] = getResources().getDrawable( R.drawable.ic_angel_and_demon_);
        drawables[11] = getResources().getDrawable( R.drawable.ic_call_black_24dp);
        drawables[12] = getResources().getDrawable( R.drawable.ic_camera_enhance_black_24dp);
        drawables[13] = getResources().getDrawable( R.drawable.ic_dialpad_black_24dp);
        drawables[14] = getResources().getDrawable( R.drawable.ic_exit_to_app_black_24dp);
        drawables[15] = getResources().getDrawable( R.drawable.ic_explore_black_24dp);

        List<Drawable> cards = Arrays.asList(drawables);
        Collections.shuffle(cards);
        cards.toArray(drawables);
    }

    public void flipper(View view,int i){
        if(!isVisible[i]){
            flipper.flipImage(drawables[i], ((ImageView) view));

        }else{
            flipper.flipImage(backside, ((ImageView) view));
        }
        isVisible[i]= !isVisible[i];
    }

    public void action(View view,int i) {
        if (!isVisible[i]) {
            if (isFirst) {
                flipper(view, i);
                isVisible[i] = true;
                isFirst = false;
            } else {
                flipper(view, i);
                isFirst = true;
                isVisible[i] = true;

                /*if(matches){
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
                Log.d("click", "onClick: ");
                action(view,0);

                break;

            case R.id.imageView1:
                action(view,1);
                break;

            case R.id.imageView2:
                action(view,2);
                break;

            case R.id.imageView3:
                action(view,3);
                break;

            case R.id.imageView8:
                action(view,4);
                break;

            case R.id.imageView9:
                action(view,5);
                break;

            case R.id.imageView10:
                action(view,6);
                break;

            case R.id.imageView11:
                action(view,7);
                break;

            case R.id.imageView16:
                action(view,8);
                break;

            case R.id.imageView17:
                action(view,9);
                break;

            case R.id.imageView18:
                action(view,10);
                break;

            case R.id.imageView19:
                action(view,11);
                break;

            case R.id.imageView24:
                action(view,12);
                break;

            case R.id.imageView25:
                action(view,13);
                break;

            case R.id.imageView26:
                action(view,14);
                break;

            case R.id.imageView27:
                action(view,15);
                break;
        }

    }

   /* @OnClick({R.id.imageView0,R.id.imageView1,R.id.imageView2,R.id.imageView3,R.id.imageView8,R.id.imageView9,R.id.imageView10,R.id.imageView11,R.id.imageView16,R.id.imageView17,R.id.imageView18,R.id.imageView19,R.id.imageView24,R.id.imageView25,R.id.imageView26,R.id.imageView27})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.imageView0:
                action(view,0);
                break;

            case R.id.imageView1:
                action(view,1);
                break;

            case R.id.imageView2:
                action(view,2);
                break;

            case R.id.imageView3:
                action(view,3);
                break;

            case R.id.imageView8:
                action(view,4);
                break;

            case R.id.imageView9:
                action(view,5);
                break;

            case R.id.imageView10:
                action(view,6);
                break;

            case R.id.imageView11:
                action(view,7);
                break;

            case R.id.imageView16:
                action(view,8);
                break;

            case R.id.imageView17:
                action(view,9);
                break;

            case R.id.imageView18:
                action(view,10);
                break;

            case R.id.imageView19:
                action(view,11);
                break;

            case R.id.imageView24:
                action(view,12);
                break;

            case R.id.imageView25:
                action(view,13);
                break;

            case R.id.imageView26:
                action(view,14);
                break;

            case R.id.imageView27:
                action(view,15);
                break;
        }
    }*/

    public void setRandomGrid(){
        Random rand = new Random();
        int a,b,aux;
        for(int i = 0;i<32;i++){

           // a = rand.nextInt(random.length+1);
           // b= rand.nextInt(random.length+1);


        }

    }
}
