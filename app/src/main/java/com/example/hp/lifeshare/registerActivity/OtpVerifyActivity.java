package com.example.hp.lifeshare.registerActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.lifeshare.PreferenceHelper;
import com.example.hp.lifeshare.R;

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
                int myOtp=Integer.parseInt(t);
                int otp= PreferenceHelper.getdetailsOtp(getApplicationContext());
            if(otp==myOtp)
            {
                Toast.makeText(OtpVerifyActivity.this, "Correct Otp "+otp+myOtp, Toast.LENGTH_SHORT).show();

            }
            else{
                Toast.makeText(OtpVerifyActivity.this, "Wrong Otp"+otp+myOtp, Toast.LENGTH_SHORT).show();
            }
            }
        });
    }
}
