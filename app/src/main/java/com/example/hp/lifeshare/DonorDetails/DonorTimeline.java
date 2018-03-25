package com.example.hp.lifeshare.DonorDetails;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.lifeshare.BeanClass.BloodbankAdapter;
import com.example.hp.lifeshare.BeanClass.BloodbankBean;
import com.example.hp.lifeshare.R;
import com.example.hp.lifeshare.recyclerview_functions.DividerItemDecoration;
import com.example.hp.lifeshare.recyclerview_functions.RecyclerTouchListener;

import java.util.List;

public class DonorTimeline extends AppCompatActivity {

    FloatingActionButton fab;

    List<BloodbankBean> bloodbanklist;
    RecyclerView recyclerView;
    BloodbankAdapter adapter;
    TextView tvinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_timeline);
        tvinfo=(TextView)findViewById(R.id.tvinfo);

        fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        setRecyclerView();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DonorTimeline.this,DonorMap.class));
            }
        });
        RecyclerViewClick();
    }

    private void setRecyclerView()
    {
/*
        bloodbanklist=null;
//        Log.e("List",""+reminderlist);
        if(bloodbanklist.size()<1)
        {
            tvinfo.setVisibility(View.VISIBLE);
        }
        else {
            tvinfo.setVisibility(View.INVISIBLE);
*/

            adapter = new BloodbankAdapter(getApplicationContext(), bloodbanklist);

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }



    public void RecyclerViewClick()
    {
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener()
        {
            @Override
            public void onClick(View view, int position) {
//                bloodbanklist.get(position).getStatus();
                Intent intent=new Intent(getApplicationContext(),ShowBloodbankDetails.class);
            //    Log.e("Reminder id",""+reminderlist.get(position).getReminder_id());
          //      intent.putExtra("Rid",String.valueOf(bloodbanklist.get(position).getReminder_id()));
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }
        ));

    }



}
