package com.example.hp.lifeshare.BeanClass;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hp.lifeshare.R;
import com.example.hp.lifeshare.recyclerview_functions.RecyclerTouchListener;

import java.util.List;
import java.util.Random;

/**
 * Created by HP on 24-03-2018.
 */

public class BloodbankAdapter extends RecyclerView.Adapter<BloodbankAdapter.MyViewHolder> {

    List<BloodbankBean> mylist;
    Context context;

    public BloodbankAdapter(Context context, List<BloodbankBean> items) {

        this.mylist = items;
        this.context = context;
    }


    class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tname,tdate;
        //        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            tname= (TextView) view.findViewById(R.id.tvbname);
            tdate= (TextView) view.findViewById(R.id.tvdate);

        }

    }

    @Override
    public int getItemCount() {
        return 0;
//        return mylist.size();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final BloodbankBean rowItem = mylist.get(position);
        holder.tname.setText(""+rowItem.getBankName());
        holder.tdate.setText("" + rowItem.getDate());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.resource_bloodbank, parent, false);
        return new BloodbankAdapter.MyViewHolder(itemView);
    }


}
