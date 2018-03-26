package com.example.hp.lifeshare.DonorDetails;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.hp.lifeshare.BloodBankDetails.BloodBankActivity;
import com.example.hp.lifeshare.PreferenceHelper;
import com.example.hp.lifeshare.R;
import com.example.hp.lifeshare.VolleyHelper;
import com.example.hp.lifeshare.registerActivity.GetEmailAcitivity;

import org.json.JSONException;
import org.json.JSONObject;

public class GetResponse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_response);
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},1);
        } if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},2);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},3);
        }

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},0);
        }

        if ("Donor".equals(PreferenceHelper.getUserType(getApplicationContext()))) {
            VolleyHelper volleyHelper = new VolleyHelper(getApplicationContext());
            volleyHelper.get("isDonorAccepted/"+PreferenceHelper.getdetailsEmail(getApplicationContext()), null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if (response.getBoolean("resp")) {
                            //   Intent.
                            startActivity(new Intent(GetResponse.this,DonorTimeline.class
                            ));
                        } else {
                            Toast.makeText(GetResponse.this, "" + response.get("error"), Toast.LENGTH_SHORT).show();
                            int errorCode = response.getInt("errorCode");
                            if (errorCode == 1) {
//                                startActivity(new Intent(GetResponse.this,DonorTimeline.class));

                               PreferenceHelper.clearDetails(getApplicationContext());
                                startActivity(new Intent(GetResponse.this, GetEmailAcitivity.class));
                                finish();

                            } else {

                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        }
        else
        if ("Bank".equals(PreferenceHelper.getUserType(getApplicationContext()))) {
            VolleyHelper volleyHelper = new VolleyHelper(getApplicationContext());
            volleyHelper.get("isBankAccepted/"+PreferenceHelper.getdetailsEmail(getApplicationContext()), null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Toast.makeText(GetResponse.this, ""+response.toString(), Toast.LENGTH_SHORT).show();

                        if (response.getBoolean("resp")) {
                               startActivity(new Intent(GetResponse.this,BloodBankActivity.class));
                               finish();
                        } else {
                            Toast.makeText(GetResponse.this, "" + response.get("error"), Toast.LENGTH_SHORT).show();
                            int errorCode = response.getInt("errorCode");
                            if (errorCode == 1) {


                                PreferenceHelper.clearDetails(getApplicationContext());
                                startActivity(new Intent(GetResponse.this, GetEmailAcitivity.class));
                                finish();

//                                startActivity(new Intent(GetResponse.this,BloodBankActivity.class));
  //                              finish();

                            } else {

                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        }
        else{

            PreferenceHelper.clearDetails(getApplicationContext());
            startActivity(new Intent(GetResponse.this, GetEmailAcitivity.class));
            finish();

//            startActivity(new Intent(GetResponse.this,BloodBankActivity.class));
  //          finish();


        }
//        startActivity(new Intent(GetResponse.this,GetEmailAcitivity.class));




    }
}