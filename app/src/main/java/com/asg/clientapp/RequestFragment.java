package com.asg.clientapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;

import es.dmoral.toasty.Toasty;


/**
 * A simple {@link Fragment} subclass.
 */
public class RequestFragment extends Fragment {

    public RequestFragment() {
        // Required empty public constructor
    }

    RewardedVideoAd rewardedVideoAd;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (rewardedVideoAd.isLoaded())
            {
                rewardedVideoAd.show();
            }

        }
    };
    FirebaseFirestore db;
    CollectionReference ref;

    View myView;
        Button btnsave,btncanel;
        EditText edtname,edtimage;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView =  inflater.inflate(R.layout.fragment_request, container, false);

        GoogleAds googleAds = new GoogleAds();
        rewardedVideoAd = googleAds.loadRewaredVideoAds(getContext());
       googleAds.loadAdsVerticalAds(myView,getContext(),getActivity());

       handler.postDelayed(runnable,5000);

        edtimage = myView.findViewById(R.id.edtimage);
        edtname=myView.findViewById(R.id.edtname);
        btncanel = myView.findViewById(R.id.btncanel);
        btnsave=myView.findViewById(R.id.btnsave);
        db=FirebaseFirestore.getInstance();
        final CollectionReference ref = db.collection(getString(R.string.request_ref));

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rewardedVideoAd.isLoaded())
                {
                    rewardedVideoAd.show();
                }

                if (!edtimage.getText().toString().trim().equals("")
                || !edtname.getText().toString().trim().equals(""))
                {
                    RequestModel model = new RequestModel();
                    model.name=edtname.getText().toString().trim();
                    model.imagelink =edtimage.getText().toString().trim();
                    SimpleDateFormat format = new SimpleDateFormat("M,d,yyyy,hh:mm:ss");
                    model.createAd = format.format(new Date());

                    ref.add(model);
                    edtname.setText("");
                    edtimage.setText("");
                    Toasty.success(getContext(),"Request Send",Toasty.LENGTH_LONG).show();

                }
                    else {
                        Toasty.error(getContext(),"Please Fill Data",Toasty.LENGTH_LONG).show();
                }
                }

        });



        return  myView;
    }



}
