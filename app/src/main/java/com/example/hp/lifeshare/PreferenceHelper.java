package com.example.hp.lifeshare;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by HP on 23-03-2018.
 */


public class PreferenceHelper {
    private  static SharedPreferences detailPref,imagePref;
    private  static SharedPreferences appPref;
    private  static SharedPreferences.Editor appEditor,detailEditor;

    public static void setdetails(Context context,String email,int otp)
    {
        detailPref=context.getSharedPreferences("detailPref",Context.MODE_PRIVATE);
        detailEditor=detailPref.edit();
        detailEditor.putString("email",email);
        detailEditor.putInt("phone",otp);
        detailEditor.commit();
    }

    public static String getdetailsEmail(Context context)
    {
        detailPref=context.getSharedPreferences("detailPref",Context.MODE_PRIVATE);
        return detailPref.getString("email",null);

    }
    public static int getdetailsOtp(Context context)
    {
        detailPref=context.getSharedPreferences("detailPref",Context.MODE_PRIVATE);
        return detailPref.getInt("otp",0);

    }





}
/*    private  static SharedPreferences detailPref,imagePref;
    private  static SharedPreferences appPref;
    private  static SharedPreferences.Editor appEditor;

    public static void setImageStatus(Context context, Boolean status)
    {
        detailPref=context.getSharedPreferences("detailPref",Context.MODE_PRIVATE);
        detailEditor=detailPref.edit();
        detailEditor.putBoolean("ImageStatus",status);
        detailEditor.commit();
    }

    public static void setdetails(Context context,String name ,String phone)
    {
        detailPref=context.getSharedPreferences("detailPref",Context.MODE_PRIVATE);
        detailEditor=detailPref.edit();
        detailEditor.putString("name",name);
        detailEditor.putString("phone",phone);
        detailEditor.commit();
    }

    public static void setdetailsImagePath(Context context,String path)
    {
        detailPref=context.getSharedPreferences("detailPref",Context.MODE_PRIVATE);
        detailEditor=detailPref.edit();
        detailEditor.putString("path",path);
        detailEditor.commit();
    }

    public static String getdetailsImagePath(Context context)
    {
        detailPref=context.getSharedPreferences("detailPref",Context.MODE_PRIVATE);
        return detailPref.getString("path",null);

    }

    public static String getDetailsName(Context context)
    {
        detailPref=context.getSharedPreferences("detailPref",Context.MODE_PRIVATE);
        return  detailPref.getString("name",null);
    }

    public static String getDetailsPhone(Context context)
    {
        detailPref=context.getSharedPreferences("detailPref",Context.MODE_PRIVATE);
        return  detailPref.getString("phone",null);
    }

    public static Boolean getImageStatus(Context context)
    {
        detailPref=context.getSharedPreferences("detailPref",Context.MODE_PRIVATE);
        return  detailPref.getBoolean("ImageStatus",false);
    }

    public static void setEmail(Context context,String email )
    {
        appPref=context.getSharedPreferences("appPref",Context.MODE_PRIVATE);
        appEditor=appPref.edit();
        appEditor.putString("email",email);
        appEditor.commit();
    }
    public static void setOtp(Context context,String otp)
    {
        appPref=context.getSharedPreferences("appPref",Context.MODE_PRIVATE);
        appEditor=appPref.edit();
        appEditor.putString("otp",otp);
        appEditor.commit();
    }

    public static void setStatus(Context context,Boolean status)
    {
        appPref=context.getSharedPreferences("appPref",Context.MODE_PRIVATE);
        appEditor=appPref.edit();
        appEditor.putBoolean("status",status);
        appEditor.commit();
    }

    public static void setSignupStatus(Context context,Boolean status)
    {
        appPref=context.getSharedPreferences("appPref",Context.MODE_PRIVATE);
        appEditor=appPref.edit();
        appEditor.putBoolean("signupstatus",status);
        appEditor.commit();
    }

    public static String getEmail(Context context)
    {
        appPref=context.getSharedPreferences("appPref",Context.MODE_PRIVATE);
        return appPref.getString("email",null);
    }

    public static String getOtp(Context context)
    {
        appPref=context.getSharedPreferences("appPref",Context.MODE_PRIVATE);
        return appPref.getString("otp",null);
    }

    public static boolean getStatus(Context context)
    {
        appPref=context.getSharedPreferences("appPref",Context.MODE_PRIVATE);
        return appPref.getBoolean("status",false);
    }

    public static boolean getSignupStatus(Context context)
    {
        appPref=context.getSharedPreferences("appPref",Context.MODE_PRIVATE);
        return appPref.getBoolean("signupstatus",false);
    }

    public static void setParticularUserDetail(Context context, UserInformation userInformation)
    {
        appPref=context.getSharedPreferences("appPref",Context.MODE_PRIVATE);
        appEditor=appPref.edit();
        appEditor.putString("username",userInformation.getUser_name());
        appEditor.putString("userPhone",userInformation.getUser_phone());
        appEditor.putString("date",userInformation.getDate());
        appEditor.putString("lat",""+userInformation.getLatitude());
        appEditor.putString("long",""+userInformation.getLongitude());
        appEditor.putString("imageuri",userInformation.getImageUri());
        appEditor.commit();
    }

    public static String getParticularUserDetailName (Context context)
    {
        appPref=context.getSharedPreferences("appPref",Context.MODE_PRIVATE);
        return appPref.getString("username",null);
    }

    public static String getParticularUserDetailPhone(Context context)
    {
        appPref=context.getSharedPreferences("appPref",Context.MODE_PRIVATE);
        return appPref.getString("userPhone",null);
    }

    public static String getParticularUserDetailImageUri(Context context)
    {
        appPref=context.getSharedPreferences("appPref",Context.MODE_PRIVATE);
        return appPref.getString("imageuri",null);
    }

    public static String getParticularUserDetailDate(Context context)
    {
        appPref=context.getSharedPreferences("appPref",Context.MODE_PRIVATE);
        return appPref.getString("date",null);
    }

    public static String getParticularUSerDetailLatitude(Context context)
    {
        appPref=context.getSharedPreferences("appPref",Context.MODE_PRIVATE);
        return appPref.getString("lat",null);
    }

    public static String getParticularUserDetailLongitude(Context context)
    {
        appPref=context.getSharedPreferences("appPref",Context.MODE_PRIVATE);
        return appPref.getString("long",null);
    }


}
*/
