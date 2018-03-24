package com.example.hp.lifeshare;

import android.content.Context;
import android.content.SharedPreferences;

public class BankPreferenceHelper {
    private  static SharedPreferences bankDetailPref;
    private  static SharedPreferences.Editor bankDetailEditor;

    public static void setdetails(Context context,int op,int on,int ap,int an,int bp,int bn,int abp,int abn)
    {
        bankDetailPref=context.getSharedPreferences("bankDetailPref",Context.MODE_PRIVATE);
        bankDetailEditor=bankDetailPref.edit();
        bankDetailEditor.putInt("o+",op);
        bankDetailEditor.putInt("o-",on);
        bankDetailEditor.putInt("a+",ap);
        bankDetailEditor.putInt("a-",an);
        bankDetailEditor.putInt("b+",bp);
        bankDetailEditor.putInt("b-",bn);
        bankDetailEditor.putInt("ab+",abp);
        bankDetailEditor.putInt("ab-",abn);
        bankDetailEditor.apply();
    }
    public static void set(Context context,String a,int op)
    {
        bankDetailPref=context.getSharedPreferences("bankDetailPref",Context.MODE_PRIVATE);
        bankDetailEditor=bankDetailPref.edit();
        bankDetailEditor.putInt(a,op);
        bankDetailEditor.apply();
    }

    public static int get(Context context,String g)
    {
        bankDetailPref=context.getSharedPreferences("bankDetailPref",Context.MODE_PRIVATE);
        return bankDetailPref.getInt(g,0);
    }





    public static int getop(Context context)
    {
        bankDetailPref=context.getSharedPreferences("bankDetailPref",Context.MODE_PRIVATE);
        return bankDetailPref.getInt("o+",0);
    }
    public static int geton(Context context)
    {
        bankDetailPref=context.getSharedPreferences("bankDetailPref",Context.MODE_PRIVATE);
        return bankDetailPref.getInt("o-",0);
    }
    public static int getap(Context context)
    {
        bankDetailPref=context.getSharedPreferences("bankDetailPref",Context.MODE_PRIVATE);
        return bankDetailPref.getInt("a+",0);
    }
    public static int getan(Context context)
    {
        bankDetailPref=context.getSharedPreferences("bankDetailPref",Context.MODE_PRIVATE);
        return bankDetailPref.getInt("a-",0);
    }    public static int getbp(Context context)
    {
        bankDetailPref=context.getSharedPreferences("bankDetailPref",Context.MODE_PRIVATE);
        return bankDetailPref.getInt("b+",0);
    }
    public static int getbn(Context context)
    {
        bankDetailPref=context.getSharedPreferences("bankDetailPref",Context.MODE_PRIVATE);
        return bankDetailPref.getInt("b-",0);
    }
    public static int getabp(Context context)
    {
        bankDetailPref=context.getSharedPreferences("bankDetailPref",Context.MODE_PRIVATE);
        return bankDetailPref.getInt("ab+",0);
    }
    public static int getabn(Context context)
    {
        bankDetailPref=context.getSharedPreferences("bankDetailPref",Context.MODE_PRIVATE);
        return bankDetailPref.getInt("ab-",0);
    }











}
