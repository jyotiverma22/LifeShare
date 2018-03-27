package com.example.hp.lifeshare;

import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.hp.lifeshare.registerActivity.MapsActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by ANUBHAV on 25-Dec-16.
 */

public class MyFirebaseInstaceIdService extends FirebaseInstanceIdService implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMarkerDragListener, GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerClickListener, LocationListener {
    private static final String RegToken="REG_TOKEN";
    private GoogleMap mMap;
    private double longitude;
    private double latitude;
    private GoogleApiClient googleApiClient;
    private static final String TAG = "MapsActivity";
    Location mLastLocation;
    Marker mCurrLocationMarker;
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String recentToken= FirebaseInstanceId.getInstance().getToken();
        FirebaseMessaging.getInstance().subscribeToTopic("all");



        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();

        Log.e(TAG,""+latitude+" "+longitude);



    }

    //Getting current location
    private void getCurrentLocation() {
//        mMap.clear();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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

            VolleyHelper volleyHelper=new VolleyHelper(MyFirebaseInstaceIdService.this);
              volleyHelper.get("subscribeMe/" + longitude + "/" + latitude, null, new Response.Listener<JSONObject>() {
                  @Override
                  public void onResponse(JSONObject response) {
                      try {
                          if(response.getBoolean("resp"))
                          {
                              FirebaseMessaging.getInstance().unsubscribeFromTopic(""+PreferenceHelper.getTopic(MyFirebaseInstaceIdService.this));
                              FirebaseMessaging.getInstance().subscribeToTopic(""+response.getString("data"));
                              PreferenceHelper.setTopic(MyFirebaseInstaceIdService.this,""+response.getString("data"));
                            //  Log.d(RegToken,recentToken);
                          }
                          else{
                              Toast.makeText(MyFirebaseInstaceIdService.this, ""+response.getString("error"), Toast.LENGTH_SHORT).show();
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

            //moving the map to location
//            moveMap();
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
     //   mCurrLocationMarker = mMap.addMarker(markerOptions);


        //move map camera
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
       // mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
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
    public void onConnected(@Nullable Bundle bundle) {
        getCurrentLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onMapLongClick(LatLng latLng) {
       // mMap.addMarker(new MarkerOptions().position(latLng).draggable(true));

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
 //       moveMap();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
      /*  mMap=googleMap;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        //  ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 001);

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
                        .title("Current Location "+latLng)); //Adding a title

                //Moving the camera
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));


                //Animating the camera
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
                String msg = latitude + ", " + longitude;
                Log.e("Location", "" + msg);
                //Displaying current coordinates in toast
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();


            }
        }*/
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
                .title("Current Location "+latLng));
        String msg="lat long="+latitude+", "+longitude;
        Log.e("location2",""+msg);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        mMap.getUiSettings().setZoomControlsEnabled(true);


    }



}
