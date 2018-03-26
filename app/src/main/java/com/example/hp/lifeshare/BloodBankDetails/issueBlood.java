package com.example.hp.lifeshare.BloodBankDetails;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hp.lifeshare.BankPreferenceHelper;
import com.example.hp.lifeshare.R;

/**
 * Created by ANUBHAV on 24-Mar-18.
 */

public class issueBlood extends Fragment {
    EditText etid,etcount;
    Button issue;
    Spinner bg;
    Context c;
    @Override
public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    //returning our layout file
    //change R.layout.yourlayoutfilename for each of your fragments
    return inflater.inflate(R.layout.issue_blood_fragment, container, false);
}


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Issue Blood");
        c= getActivity().getApplicationContext();
        etcount = (EditText)  getActivity().findViewById(R.id.etunits);
        etid = (EditText)  getActivity().findViewById(R.id.etpatient_id);
        issue =(Button)  getActivity().findViewById(R.id.issuebt);
        bg =(Spinner)  getActivity().findViewById(R.id.etbloodGroup);
    }

    @Override
    public void onResume() {
        super.onResume();
        String[] bgroups=new String[]{"O+","O-","A+","A-","B+","B-","AB+","AB-"};
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(c,android.R.layout.simple_spinner_item,bgroups);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bg.setAdapter(adapter);

       final BloodBankHistoryItem bloodBankHistoryItem = new BloodBankHistoryItem();
        bg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String a = parent.getAdapter().getItem(position).toString();
                bloodBankHistoryItem.setGroup(a);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        //  Log.e("Repeat",""+Repeat);

        issue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandlerBankHistory db = new DatabaseHandlerBankHistory(c);
                int id =Integer.parseInt( etid.getText().toString());
                int count =Integer.parseInt( etcount.getText().toString());
                bloodBankHistoryItem.setCount(count);
                bloodBankHistoryItem.setPatient_id(id);


                int oldCount = BankPreferenceHelper.get(c,bloodBankHistoryItem.getGroup());
                if(oldCount >= count){
                    Log.i("abc",bloodBankHistoryItem.toString());
                    db.additem(bloodBankHistoryItem);
                    BankPreferenceHelper.set(c,bloodBankHistoryItem.getGroup(),oldCount-count);

                }
                else {
                    Toast.makeText(c, "not enough blood available", Toast.LENGTH_SHORT).show();
                }
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new currentDetails());
                ft.commit();
            }
        });
    }
}
