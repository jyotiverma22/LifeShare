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
        detailEditor.putInt("otp",otp);
        detailEditor.commit();
    }
    public static void setDonorDetails(Context context,String name,String dob)
    {
        detailPref=context.getSharedPreferences("detailPref",Context.MODE_PRIVATE);
        detailEditor=detailPref.edit();
        detailEditor.putString("name",name);
        detailEditor.putString("dob",dob);
        detailEditor.commit();
    }
    public static void setBloodBankDetails(Context context,String name,String phone,String address)
    {
        detailPref=context.getSharedPreferences("detailPref",Context.MODE_PRIVATE);
        detailEditor=detailPref.edit();
        detailEditor.putString("name",name);
        detailEditor.putString("phone",phone);
        detailEditor.putString("address",address);
        detailEditor.commit();
    }
    public static void setDonorResponse(Context context,Boolean resp)
    {
        detailPref=context.getSharedPreferences("detailPref",Context.MODE_PRIVATE);
        detailEditor=detailPref.edit();
        detailEditor.putBoolean("resp",resp);
        detailEditor.commit();
    }

    public static Boolean getDonorResp(Context context)
    {
        detailPref=context.getSharedPreferences("detailPref",Context.MODE_PRIVATE);
        return detailPref.getBoolean("resp",false);

    }



    public static void setDonorFront(Context context,String front)
    {
        detailPref=context.getSharedPreferences("detailPref",Context.MODE_PRIVATE);
        detailEditor=detailPref.edit();
        detailEditor.putString("front",front);
        detailEditor.commit();
    }
    public static void setDonorFrontImage(Context context,String front,String back)
    {
        detailPref=context.getSharedPreferences("detailPref",Context.MODE_PRIVATE);
        detailEditor=detailPref.edit();
        detailEditor.putString("frontimage",front);

        detailEditor.commit();
    }

    public static void setDonorBack(Context context,String back)
    {
        detailPref=context.getSharedPreferences("detailPref",Context.MODE_PRIVATE);
        detailEditor=detailPref.edit();
        detailEditor.putString("back",back);
        detailEditor.commit();
    }
    public static void setDonorBgroup(Context context,String group)
    {
        detailPref=context.getSharedPreferences("detailPref",Context.MODE_PRIVATE);
        detailEditor=detailPref.edit();
        detailEditor.putString("group",group);
        detailEditor.commit();
    }

    public static String getdetailsBgroup(Context context)
    {
        detailPref=context.getSharedPreferences("detailPref",Context.MODE_PRIVATE);
        return detailPref.getString("group",null);

    }

    public static String getdetailsEmail(Context context)
    {
        detailPref=context.getSharedPreferences("detailPref",Context.MODE_PRIVATE);
        return detailPref.getString("email",null);

    }
    public static String getdetailsName(Context context)
    {
        detailPref=context.getSharedPreferences("detailPref",Context.MODE_PRIVATE);
        return detailPref.getString("name",null);

    }
    public static String getdetailsDob(Context context)
    {
        detailPref=context.getSharedPreferences("detailPref",Context.MODE_PRIVATE);
        return detailPref.getString("dob",null);

    }
    public static String getdetailsFront(Context context)
    {
        detailPref=context.getSharedPreferences("detailPref",Context.MODE_PRIVATE);
        return detailPref.getString("front",null);

    }
    public static String getdetailsBack(Context context)
    {
        detailPref=context.getSharedPreferences("detailPref",Context.MODE_PRIVATE);
        return detailPref.getString("back",null);

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
