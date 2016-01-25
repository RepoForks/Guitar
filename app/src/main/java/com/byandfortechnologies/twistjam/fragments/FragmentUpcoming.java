package com.byandfortechnologies.twistjam.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.byandfortechnologies.twistjam.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentUpcoming#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentUpcoming extends Fragment{
    //The key used to store arraylist of movie objects to and from parcelable
    private static final String STATE_MOVIES = "state_movies";

    public FragmentUpcoming() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentUpcoming.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentUpcoming newInstance(String param1, String param2) {
        FragmentUpcoming fragment = new FragmentUpcoming();
               return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_upcoming, container, false);
           return layout;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
