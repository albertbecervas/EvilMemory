package com.evilmem.albert.evilmemory.Fragments;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.evilmem.albert.evilmemory.Adapter.MyCustomAdapter;
import com.evilmem.albert.evilmemory.Adapter.RankingPlayer;
import com.evilmem.albert.evilmemory.Data.LoginHelper;
import com.evilmem.albert.evilmemory.Interface.OnFragmentInteractionListener;
import com.evilmem.albert.evilmemory.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Ranking6 extends Fragment{

    Button delete;

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayout;

    ArrayList<RankingPlayer> rankingPlayers;

    MyCustomAdapter myAdapter;

    LoginHelper loginHelper;

    public Ranking6() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View rootView = inflater.inflate(R.layout.fragment_ranking6, container, false);

        loginHelper = new LoginHelper(getActivity().getApplicationContext());

        rankingPlayers = new ArrayList<>(0);


        Cursor cursor = loginHelper.getRanking6();
        RankingPlayer pos;
        if (cursor.moveToFirst()) {
            do {
                String u = cursor.getString(cursor.getColumnIndex("name"));
                int p = cursor.getInt(cursor.getColumnIndex("score6"));
                pos = new RankingPlayer(0, u, p);
                rankingPlayers.add(pos);
            } while (cursor.moveToNext());
        }



        delete=(Button) rootView.findViewById(R.id.button);

        delete.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              LoginHelper LoginHelper = new LoginHelper(getActivity().getApplicationContext());
              LoginHelper.DeleteRanking6();
              Cursor cursor = loginHelper.getRanking6();
              RankingPlayer pos;
              if (cursor.moveToFirst()) {
                  do {
                      String u = cursor.getString(cursor.getColumnIndex("name"));
                      int p = cursor.getInt(cursor.getColumnIndex("score6"));
                      pos = new RankingPlayer(0, u, p);
                      rankingPlayers.add(pos);
                  } while (cursor.moveToNext());
              }

              myAdapter.setData(rankingPlayers);
              myAdapter.notifyDataSetChanged();

          }

        });

        //findViewById del layout activity_main
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.mRecyclerView);

        //LinearLayoutManager necesita el contexto de la Activity.
        //El LayoutManager se encarga de posicionar los items dentro del recyclerview
        //Y de definir la politica de reciclaje de los items no visibles.
        mLinearLayout = new LinearLayoutManager(getActivity());

        //Asignamos el LinearLayoutManager al recycler:
        mRecyclerView.setLayoutManager(mLinearLayout);

         myAdapter = new MyCustomAdapter(rankingPlayers);


        //El adapter se encarga de  adaptar un objeto definido en el c�digo a una vista en xml
        //seg�n la estructura definida.
        //Asignamos nuestro custom Adapter
        mRecyclerView.setAdapter(myAdapter);



        // Inflate the layout for this fragment
        return rootView;

    }



    OnFragmentInteractionListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mListener = (OnFragmentInteractionListener) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
