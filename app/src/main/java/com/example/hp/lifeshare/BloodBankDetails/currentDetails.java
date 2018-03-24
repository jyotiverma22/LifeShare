package com.example.hp.lifeshare.BloodBankDetails;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.lifeshare.BankPreferenceHelper;
import com.example.hp.lifeshare.R;

/**
 * Created by ANUBHAV on 24-Mar-18.
 */

public class currentDetails extends Fragment {
    TextView op,on,ap,an,bp,bn,abp,abn,total;
    Context c;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.current_details_fragment, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Current Status");


    }

    @Override
    public void onResume() {
        super.onResume();
        op = (TextView) getActivity().findViewById(R.id.op);
        on = (TextView) getActivity().findViewById(R.id.on);
        ap = (TextView) getActivity().findViewById(R.id.ap);
        an = (TextView) getActivity().findViewById(R.id.an);
        bp = (TextView) getActivity().findViewById(R.id.bp);
        bn = (TextView) getActivity().findViewById(R.id.bn);
        abp = (TextView) getActivity().findViewById(R.id.abp);
        abn = (TextView) getActivity().findViewById(R.id.abn);
        total = (TextView) getActivity().findViewById(R.id.total);
        c = getActivity().getApplicationContext();
        int opu = BankPreferenceHelper.getop(c);
        int onu = BankPreferenceHelper.geton(c);
        int apu = BankPreferenceHelper.getap(c);
        int anu = BankPreferenceHelper.getan(c);
        int bpu = BankPreferenceHelper.getbp(c);
        int bnu = BankPreferenceHelper.getbn(c);
        int abpu = BankPreferenceHelper.getabp(c);
        int abnu = BankPreferenceHelper.getabn(c);
        int totalu = opu+onu+apu+anu+bpu+bnu+abpu+abnu;
        op.setText(opu+"");
        on.setText(onu+"");
        ap.setText(apu+"");
        an.setText(anu+"");
        bp.setText(bpu+"");
        bn.setText(bnu+"");
        abp.setText(abpu+"");
        abn.setText(abnu+"");
        total.setText(totalu+"");
    }
}
