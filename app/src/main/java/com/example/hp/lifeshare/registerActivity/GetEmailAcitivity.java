package com.example.hp.lifeshare.registerActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.hp.lifeshare.NetworkConnection;
import com.example.hp.lifeshare.PreferenceHelper;
import com.example.hp.lifeshare.R;
import com.example.hp.lifeshare.VolleyHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Random;

public class GetEmailAcitivity extends AppCompatActivity {
Button bemail;
EditText etemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_email_acitivity);
        etemail=(EditText)findViewById(R.id.temail);
        bemail=(Button)findViewById(R.id.bemail);
        bemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(NetworkConnection.isNetworkAvailable(getApplicationContext())) {
                    String email = etemail.getText().toString();
                    // Toast.makeText(GetEmailAcitivity.this, "On Button click", Toast.LENGTH_SHORT).show();
//                int otp=1234;
                    Random generator = new Random();
                    int n = 8000;
                    n = generator.nextInt(n);
                    Log.e("otp", "" + (n + 1000));

                    int otp = (n + 1000);

                    Log.e("otp", "" + otp);
                    PreferenceHelper.setdetails(getApplicationContext(), email, otp);


                    sendEmail(email, otp);
                }
                else
                {
                    Toast.makeText(GetEmailAcitivity.this, "Network not available", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public boolean sendEmail(String email,int otp)
    {/*
        startActivity(new Intent(GetEmailAcitivity.this,OtpVerifyActivity.class));
        finish();*/

        //boolean resp;
        try {
            JSONObject json = new JSONObject();

            json.put("email", email);
            json.put("otp", otp);

//            Toast.makeText(this, "in fuction", Toast.LENGTH_SHORT).show();

            VolleyHelper volleyHelper=new VolleyHelper(getApplicationContext());
            volleyHelper.post("verifyemail", json, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i("resp",""+response.toString());
                    try{
                        response.getBoolean("resp");
                        startActivity(new Intent(GetEmailAcitivity.this,OtpVerifyActivity.class));
                        finish();

                    }
                    catch (Exception e){

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("resp",error.toString());

                }
            });

        }
        catch(Exception e){

            Log.e("exception in json",""+e);
        }
        return true;

    }
}
