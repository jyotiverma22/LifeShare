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
import com.example.hp.lifeshare.R;
import com.example.hp.lifeshare.VolleyHelper;

import org.json.JSONArray;
import org.json.JSONObject;

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
            String email=etemail.getText().toString();
                Toast.makeText(GetEmailAcitivity.this, "On Button click", Toast.LENGTH_SHORT).show();
                sendEmail(email);
            }
        });

    }

    public boolean sendEmail(String email)
    {
        //boolean resp;
        try {
            JSONObject json = new JSONObject();

            json.put("email", email);
            json.put("otp", 1234);
//            Toast.makeText(this, "in fuction", Toast.LENGTH_SHORT).show();

            VolleyHelper volleyHelper=new VolleyHelper(getApplicationContext());
            volleyHelper.post("verifyemail", json, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i("resp",""+response.toString());
                    try{
                         response.getBoolean("resp");
                        Intent 

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
