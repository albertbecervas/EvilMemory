package com.evilmem.albert.evilmemory.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.evilmem.albert.evilmemory.Data.LoginHelper;
import com.evilmem.albert.evilmemory.Interface.OnFragmentInteractionListener;
import com.evilmem.albert.evilmemory.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Ranking8 extends Fragment {


    Button delete;

    public Ranking8() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_ranking8, container, false);

        delete=(Button) rootView.findViewById(R.id.button);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginHelper LoginHelper = new LoginHelper(getActivity().getApplicationContext());
                LoginHelper.DeleteRanking8();

            }

        });
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
