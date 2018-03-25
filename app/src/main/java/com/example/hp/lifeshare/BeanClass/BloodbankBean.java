package com.example.hp.lifeshare.BeanClass;

import com.example.hp.lifeshare.registerActivity.BloodBankMaps;

/**
 * Created by HP on 24-03-2018.
 */

public class BloodbankBean {

    String BankName;
    String Date;
    BloodbankBean(String name, String date)
    {
        BankName=name;
        this.Date=date;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

}
