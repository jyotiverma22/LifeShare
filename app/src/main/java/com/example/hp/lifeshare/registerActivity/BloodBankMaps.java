package com.example.hp.lifeshare.registerActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.hp.lifeshare.DonorDetails.GetResponse;
import com.example.hp.lifeshare.PreferenceHelper;
import com.example.hp.lifeshare.R;
import com.example.hp.lifeshare.VolleyHelper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class BloodBankMaps extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMarkerDragListener, GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerClickListener, LocationListener {

    private GoogleMap mMap;
    private double longitude;
    private double latitude;
    private GoogleApiClient googleApiClient;
    private static final String TAG = "MapsActivity";
    Location mLastLocation;
    Marker mCurrLocationMarker;
    FloatingActionButton fab;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    // Uri fileUri;
    Uri downloadUrl;
    JSONObject jsonObject;


    private StorageReference mStorageRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        fab=(FloatingActionButton)findViewById(R.id.fab);
        jsonObject=new JSONObject();


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mStorageRef = FirebaseStorage.getInstance().getReference();
        //Initializing googleApiClient
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BloodBankMaps.this, ""+latitude+""+longitude, Toast.LENGTH_SHORT).show();
                String msg="lat long="+latitude+", "+longitude;
                try{
                    jsonObject.put("latitude",latitude);
                    jsonObject.put("longitude",longitude);
                }
                catch (Exception e)
                {}
                Log.e("locationfab",""+msg);
                saveUserInformation(latitude,longitude);

            }
        });


    }

    private void saveUserInformation(double lat,double longitude)
    {
        String email=PreferenceHelper.getdetailsEmail(getApplicationContext());
        try {
            jsonObject.put("email", "" + email);
            jsonObject.put("phoneNo",""+PreferenceHelper.getdetailsPhone(getApplicationContext()));
            jsonObject.put("name",PreferenceHelper.getdetailsName(getApplicationContext())+"");
            JSONObject date=new JSONObject();
        }
        catch (Exception e)
        {

        }
        Uri file1 = Uri.fromFile(new File(PreferenceHelper.getdetailsFront(getApplicationContext())));
        uploadFile(file1,lat,longitude,email);


        //uploadFile(file1,file2,lat,longs);
        //Log.e("duri",""+downloadUrl);

    }



    public void uploadFile(Uri filepath,final double latitude,final double longitude,final  String email)
    {

        if(filepath!=null) {
            //displaying a progress dialog while upload is going on
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("File 1 Uploading");
            progressDialog.show();

            StorageReference imageRef = mStorageRef.child(""+email+"/front.jpg");
            //    downloadUrl;
            imageRef.putFile(filepath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            //if the upload is successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();
                            downloadUrl = taskSnapshot.getDownloadUrl();
                            try{
                                jsonObject.put("document",downloadUrl+"");
                            }
                            catch (Exception e)
                            {

                            }
                            Log.e("Uri",""+downloadUrl.toString());
                            Toast.makeText(BloodBankMaps.this, "Information Saved...", Toast.LENGTH_LONG).show();
                            VolleyHelper volleyHelper=new VolleyHelper(getApplicationContext());
                            volleyHelper.post("registerBank", jsonObject, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                        Log.e("resp",""+response);
                                    try {

                                        if(response.getBoolean("resp"))
                                        {
                                            Toast.makeText(BloodBankMaps.this, "true", Toast.LENGTH_SHORT).show();
                                            PreferenceHelper.setDonorResponse(getApplicationContext(),true);
                                            PreferenceHelper.setUserTyoe(getApplicationContext(),"Bank");
                                            startActivity(new Intent(BloodBankMaps.this, GetResponse.class));

                                            finish();
                                        }
                                        else{
                                            Toast.makeText(BloodBankMaps.this, ""+response.get("error"), Toast.LENGTH_SHORT).show();
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
                            startActivity(new Intent(BloodBankMaps.this, TabActivity.class));

//                            startActivity(new Intent(MapsActivity.this, TabActivity.class));
                            //         return downloadUrl;
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            //if the upload is not successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();
                            //and displaying error message
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            Toast.makeText(BloodBankMaps.this, "not uploaded", Toast.LENGTH_SHORT).show();
                            // Handle unsuccessful uploads
                            // ...
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                            //displaying percentage in progress dialog
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    });
        }

    }

    //Getting current location
    private void getCurrentLocation() {
//        mMap.clear();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (location != null) {
            //Getting longitude and latitude
            longitude = location.getLongitude();
            latitude = location.getLatitude();

            //moving the map to location
            moveMap();
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
            mMap=googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(BloodBankMaps.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 001);

        } else {

            Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            if (location != null) {
                //Getting longitude an
                // d latitude
                longitude = location.getLongitude();
                latitude = location.getLatitude();
                //Getting longitude and latitude
                //moving the map to location
                LatLng latLng = new LatLng(latitude, longitude);

                //Adding marker to map
                mMap.addMarker(new MarkerOptions()
                        .position(latLng) //setting position
                        .draggable(true) //Making the marker draggable
                        .title("Current Location")); //Adding a title

                //Moving the camera
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));


                //Animating the camera
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
                String msg = latitude + ", " + longitude;
                Log.e("Location", "" + msg);
                //Displaying current coordinates in toast
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();


            }
        }
    }

    @Override
    protected void onStart() {
        googleApiClient.connect();

        super.onStart();
    }

    @Override
    protected void onStop() {
        googleApiClient.connect();

        super.onStop();
    }

    private void moveMap() {
        /**
         * Creating the latlng object to store lat, long coordinates
         * adding marker to map
         * move the camera with animation
         */
        LatLng latLng = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .draggable(true)
                .title("Current Location"));
        String msg="lat long="+latitude+", "+longitude;
        Log.e("location2",""+msg);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        mMap.getUiSettings().setZoomControlsEnabled(true);


    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mMap.addMarker(markerOptions);


        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
        String msg = latitude + ", " + longitude;
        Log.e("Locationchanged", "" + msg);

        Toast.makeText(this, ""+msg, Toast.LENGTH_SHORT).show();



    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        return true;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        latitude = marker.getPosition().latitude;
        longitude = marker.getPosition().longitude;

        //move to current position
        moveMap();

    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        // mMap.clear();
        mMap.addMarker(new MarkerOptions().position(latLng).draggable(true));
    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        getCurrentLocation();
    }

}
