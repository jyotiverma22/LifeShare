package com.example.hp.lifeshare.BloodBankDetails;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hp.lifeshare.BankPreferenceHelper;
import com.example.hp.lifeshare.R;

/**
 * Created by ANUBHAV on 24-Mar-18.
 */

public class updateDetails extends Fragment {
    EditText etop,eton,etap,etan,etbp,etbn,etabp,etabn;
    Button save;
    Context c;
    @Override
public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    //returning our layout file
    //change R.layout.yourlayoutfilename for each of your fragments
    return inflater.inflate(R.layout.update_details_fragment, container, false);
}


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Update Status");
    }

    @Override
    public void onResume() {
        super.onResume();
        etop = (EditText) getActivity().findViewById(R.id.etop);
        eton = (EditText) getActivity().findViewById(R.id.eton);
        etap = (EditText) getActivity().findViewById(R.id.etap);
        etan = (EditText) getActivity().findViewById(R.id.etan);
        etbp = (EditText) getActivity().findViewById(R.id.etbp);
        etbn = (EditText) getActivity().findViewById(R.id.etbn);
        etabp = (EditText) getActivity().findViewById(R.id.etabp);
        etabn = (EditText) getActivity().findViewById(R.id.etabn);
        save = (Button) getActivity().findViewById(R.id.save);
        c = getActivity().getApplicationContext();
        int opu = BankPreferenceHelper.getop(c);
        int onu = BankPreferenceHelper.geton(c);
        int apu = BankPreferenceHelper.getap(c);
        int anu = BankPreferenceHelper.getan(c);
        int bpu = BankPreferenceHelper.getbp(c);
        int bnu = BankPreferenceHelper.getbn(c);
        int abpu = BankPreferenceHelper.getabp(c);
        int abnu = BankPreferenceHelper.getabn(c);
        etop.setText(opu+"");
        eton.setText(onu+"");
        etap.setText(apu+"");
        etan.setText(anu+"");
        etbp.setText(bpu+"");
        etbn.setText(bnu+"");
        etabp.setText(abpu+"");
        etabn.setText(abnu+"");
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int opu = Integer.parseInt(etop.getText().toString());
                int onu = Integer.parseInt(eton.getText().toString());
                int apu = Integer.parseInt(etap.getText().toString());
                int anu = Integer.parseInt(etan.getText().toString());
                int bpu = Integer.parseInt(etbp.getText().toString());
                int bnu = Integer.parseInt(etbn.getText().toString());
                int abpu = Integer.parseInt(etabp.getText().toString());
                int abnu = Integer.parseInt(etabn.getText().toString());
                BankPreferenceHelper.setdetails(c,opu,onu,apu,anu,bpu,bnu,abpu,abnu);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new currentDetails());
                ft.commit();
            }
        });

    }


}
