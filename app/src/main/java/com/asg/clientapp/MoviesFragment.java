package com.asg.clientapp;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import recycler.coverflow.RecyclerCoverFlow;


/**
 * A simple {@link Fragment} subclass.
 */

public class MoviesFragment extends Fragment {

    static TextView txtallmovie;
    static RecyclerView allmovies;
    static RecyclerCoverFlow list;
    public MoviesFragment() {
        // Required empty public constructor
    }

    View myView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView =  inflater.inflate(R.layout.fragment_movies, container, false);
       GoogleAds googleAds = new GoogleAds();
       googleAds.loadAdsVerticalAds(myView,getContext(),getActivity());
        txtallmovie=myView.findViewById(R.id.txtallmovie);
       allmovies = myView.findViewById(R.id.allmoive);
       list = myView.findViewById(R.id.list);
       activity = getActivity();
       FirebaseConnect fConnect = new FirebaseConnect(getContext(),getFragmentManager());

       fConnect.getAllMoviesByMovieFragment();
       fConnect.getTopTenMovies();;

       return  myView;
    }

    FrameLayout container1,container2;
    AdView adView1,adView2;




    public static Activity activity;
    public static void setHeader(String header)
    {
        activity.setTitle(header);
    }
}
