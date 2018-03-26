package com.example.hp.lifeshare;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.hp.lifeshare.BloodBankDetails.BloodBankActivity;
import com.example.hp.lifeshare.DonorDetails.DonorTimeline;
import com.example.hp.lifeshare.DonorDetails.GetResponse;
import com.example.hp.lifeshare.registerActivity.BloodBankMaps;
import com.example.hp.lifeshare.registerActivity.GetEmailAcitivity;
import com.example.hp.lifeshare.registerActivity.MapsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (PreferenceHelper.getDonorResp(getApplicationContext())) {

                    final Intent mainIntent = new Intent(MainActivity.this, GetResponse.class);
                    MainActivity.this.startActivity(mainIntent);
                    MainActivity.this.finish();

                } else {

                    final Intent mainIntent = new Intent(MainActivity.this, GetEmailAcitivity.class);
                    MainActivity.this.startActivity(mainIntent);


                }
            }

        }, 1000);


    }


}

