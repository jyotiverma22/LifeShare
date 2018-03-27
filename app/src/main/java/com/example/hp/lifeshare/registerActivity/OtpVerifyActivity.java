package com.example.hp.lifeshare.registerActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.hp.lifeshare.BloodBankDetails.BloodBankActivity;
import com.example.hp.lifeshare.DonorDetails.DonorTimeline;
import com.example.hp.lifeshare.DonorDetails.GetResponse;
import com.example.hp.lifeshare.PreferenceHelper;
import com.example.hp.lifeshare.R;
import com.example.hp.lifeshare.VolleyHelper;

import org.json.JSONException;
import org.json.JSONObject;

public class OtpVerifyActivity extends AppCompatActivity {

    EditText et1,et2,et3,et4;
    Button bsubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify);
        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        et3=(EditText)findViewById(R.id.et3);
        et4=(EditText)findViewById(R.id.et4);
        bsubmit=(Button)findViewById(R.id.bsubmit);
        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(et1.getText().toString().length()>=0)
                {
                    et1.clearFocus();
                    et2.requestFocus();

                }

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub
            }
        });

        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(et2.getText().toString().length()>=0)
                {
                    et2.clearFocus();
                    et3.requestFocus();

                }
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub
            }
        });

        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                if(et3.getText().toString().length()>=0)
                {
                    et3.clearFocus();
                    et4.requestFocus();

                }
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub
            }
        });
        et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(et4.getText().toString().length()>=0)
                {
                }

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub
            }
        });

        bsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t=et1.getText().toString()+et2.getText().toString()+et3.getText().toString()+et4.getText().toString();
               final int myOtp=Integer.parseInt(t);
                final int otp= PreferenceHelper.getdetailsOtp(getApplicationContext());
            if(otp==myOtp)
            {

                VolleyHelper volleyHelper=new VolleyHelper(getApplicationContext());
                volleyHelper.get("isOldUser/" + PreferenceHelper.getdetailsEmail(getApplicationContext()), null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        int respCode;
                        Toast.makeText(OtpVerifyActivity.this, "Correct Otp "+otp+myOtp, Toast.LENGTH_SHORT).show();

                        try {
                             respCode=response.getInt("respCode");
                            if(respCode==1)
                            {
                                PreferenceHelper.setUserTyoe(getApplicationContext(),"Bank");
                                PreferenceHelper.setDonorResponse(getApplicationContext(),true);
                                startActivity(new Intent(OtpVerifyActivity.this, BloodBankActivity.class));
                                finish();


                            }
                            else if(respCode==2)
                            {

                                PreferenceHelper.setUserTyoe(getApplicationContext(),"Donor");
                                PreferenceHelper.setDonorResponse(getApplicationContext(),true);
                                startActivity(new Intent(OtpVerifyActivity.this, DonorTimeline.class));
                                finish();

                            }
                            else if(respCode==3){
                                PreferenceHelper.setUserTyoe(getApplicationContext(),"Bank");
                                PreferenceHelper.setDonorResponse(getApplicationContext(),true);
                                startActivity(new Intent(OtpVerifyActivity.this, GetResponse.class));
                                finish();
                            }
                            else if(respCode==4){
                                PreferenceHelper.setUserTyoe(getApplicationContext(),"Donor");
                                PreferenceHelper.setDonorResponse(getApplicationContext(),true);
                                startActivity(new Intent(OtpVerifyActivity.this, GetResponse.class));
                                finish();


                            }
                            else if(respCode==5){
                                startActivity(new Intent(OtpVerifyActivity.this,TabActivity.class));
                                finish();

                            }



                        } catch (JSONException e) {
                            Toast.makeText(OtpVerifyActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(OtpVerifyActivity.this, "Wrong Otp"+otp+myOtp, Toast.LENGTH_SHORT).show();
            }
            }
        });
    }
}
