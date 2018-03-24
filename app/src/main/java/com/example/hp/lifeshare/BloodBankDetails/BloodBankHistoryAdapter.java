package com.example.hp.lifeshare.BloodBankDetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hp.lifeshare.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ANUBHAV on 24-Mar-18.
 */

public class BloodBankHistoryAdapter extends BaseAdapter {

    private Context context;
    private List<BloodBankHistoryItem> list;


    BloodBankHistoryAdapter(Context context, List<BloodBankHistoryItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        System.gc();
        FolderHolder holder = new FolderHolder();
        convertView= LayoutInflater.from(context).inflate(R.layout.history_item,parent,false);


        BloodBankHistoryItem item=(BloodBankHistoryItem) getItem(position);
        holder.id = (TextView) convertView.findViewById(R.id.patient_id_show);
        holder.units = (TextView) convertView.findViewById(R.id.units_show);
        holder.time = (TextView) convertView.findViewById(R.id.time_show);
        holder.group = (TextView) convertView.findViewById(R.id.bloodGroup_show);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        holder.id.setText(item.getPatient_id()+"");
        holder.units.setText(item.getCount()+"");
        holder.time.setText(formatter.format(new Date(Long.parseLong(item.getTime()))).toString());
        holder.group.setText(item.getGroup());

        return convertView;
    }
    private static class FolderHolder {

        TextView id,units,time,group;

    }

}
