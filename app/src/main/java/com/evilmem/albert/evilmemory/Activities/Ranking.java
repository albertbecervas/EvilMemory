package com.evilmem.albert.evilmemory.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.evilmem.albert.evilmemory.Fragments.Ranking4;
import com.evilmem.albert.evilmemory.Fragments.Ranking6;
import com.evilmem.albert.evilmemory.Fragments.Ranking8;
import com.evilmem.albert.evilmemory.Interface.OnFragmentInteractionListener;
import com.evilmem.albert.evilmemory.R;

//implements OnFragmentInteractionListener
public class Ranking extends drawer {

    BottomNavigationView bottomNavigationItemView;

    //TextView first,second,third,fourth,fifth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        bottomNavigationItemView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationItemView.getMenu().getItem(0).setChecked(true);

        //Load fragment for first time
        Fragment f = new Ranking4();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.ranking_container, f, "FRAGMENt")
                .commit();



        bottomNavigationItemView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.rank4:
                                Toast.makeText(getApplicationContext(),"4",Toast.LENGTH_SHORT).show();
                                Fragment f = new Ranking4();
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.ranking_container, f, "FRAGMENt")
                                        .commit();
                                break;
                            case R.id.rank6:
                                Toast.makeText(getApplicationContext(),"6",Toast.LENGTH_SHORT).show();
                                Fragment f2 = new Ranking6();
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.ranking_container, f2, "ranking6")
                                        .commit();
                                break;
                            case R.id.rank8:
                                Toast.makeText(getApplicationContext(),"8",Toast.LENGTH_SHORT).show();
                                Fragment f3 = new Ranking8();
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.ranking_container, f3, "ranking8")
                                        .commit();
                                break;

                        }
                        return true;
                    }
                });
    }


}
