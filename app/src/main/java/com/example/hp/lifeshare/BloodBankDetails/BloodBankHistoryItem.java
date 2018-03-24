package com.example.hp.lifeshare.BloodBankDetails;

import java.util.Date;

/**
 * Created by ANUBHAV on 24-Mar-18.
 */

public class BloodBankHistoryItem {
    int patient_id,count;
    String group,time;

    public BloodBankHistoryItem(int patient_id, String group, int count,String time) {
        this.patient_id = patient_id;
        this.count = count;
        this.group = group;
        this.time = time;
    }

    public BloodBankHistoryItem() {
        this.patient_id = 0;
        this.count = 0;
        this.group = "";
        this.time = System.currentTimeMillis()+"";
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
