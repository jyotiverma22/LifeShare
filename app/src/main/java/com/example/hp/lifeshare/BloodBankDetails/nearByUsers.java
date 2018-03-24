package com.example.hp.lifeshare.BloodBankDetails;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp.lifeshare.R;

/**
 * Created by ANUBHAV on 24-Mar-18.
 */

public class nearByUsers extends Fragment {
    @Override
public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    //returning our layout file
    //change R.layout.yourlayoutfilename for each of your fragments
    return inflater.inflate(R.layout.near_by_users_fragment, container, false);
}


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Near By Users");
    }
}
