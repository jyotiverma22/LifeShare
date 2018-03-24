package com.example.hp.lifeshare.BloodBankDetails;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hp.lifeshare.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ANUBHAV on 24-Mar-18.
 */

public class history extends Fragment {    @Override
public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    //returning our layout file
    //change R.layout.yourlayoutfilename for each of your fragments
    return inflater.inflate(R.layout.history_fragment, container, false);
}


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("History");
    }

    @Override
    public void onResume() {
        super.onResume();
        Context c =getActivity().getApplicationContext();
        DatabaseHandlerBankHistory databaseHandlerBankHistory = new DatabaseHandlerBankHistory(c);
        List<BloodBankHistoryItem> mylist = new ArrayList<>();
        ListView myListView;
        mylist.clear();

        mylist = databaseHandlerBankHistory.getAllHistory();
        Toast.makeText(c, ""+mylist.size(), Toast.LENGTH_SHORT).show();
        myListView=(ListView)getActivity().findViewById(R.id.history_list);
        myListView.setAdapter(new BloodBankHistoryAdapter(c, mylist));
    }
}
