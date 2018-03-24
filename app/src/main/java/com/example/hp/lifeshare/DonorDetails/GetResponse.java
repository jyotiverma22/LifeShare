package com.example.hp.lifeshare.DonorDetails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
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
        if ("Donor".equals(PreferenceHelper.getUserType(getApplicationContext()))) {
            VolleyHelper volleyHelper = new VolleyHelper(getApplicationContext());
            volleyHelper.get("isDonorAccepted", null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if (response.getBoolean("resp")) {
                            //   Intent.
                        } else {
                            Toast.makeText(GetResponse.this, "" + response.get("error"), Toast.LENGTH_SHORT).show();
                            int errorCode = response.getInt("errorCode");
                            if (errorCode == 2) {
                                PreferenceHelper.clearDetails(getApplicationContext());
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
            volleyHelper.get("isBankAccepted", null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if (response.getBoolean("resp")) {
                            //   Intent.
                        } else {
                            Toast.makeText(GetResponse.this, "" + response.get("error"), Toast.LENGTH_SHORT).show();
                            int errorCode = response.getInt("errorCode");
                            if (errorCode == 2) {

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
        else{
            PreferenceHelper.clearDetails(getApplicationContext());
            startActivity(new Intent(GetResponse.this, GetEmailAcitivity.class));
            finish();

        }



    }
}