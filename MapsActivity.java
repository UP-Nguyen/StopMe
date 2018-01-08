package com.example.admin.ugh;

import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private EditText locationName;
    private GoogleMap mMap;
    private int _x;
    private int _y;
    private String _inputtext;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationName = (EditText) findViewById(R.id.editText);
        locationName.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    //Toast.makeText(MapsActivity.this, locationName.getText(), Toast.LENGTH_SHORT).show();
                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                    try {
                        List<Address> addresses = geocoder.getFromLocationName(locationName.getText().toString(), 10);
                        if(addresses != null && !addresses.isEmpty())
                        {
                            Address address1 = addresses.get(0);
                            double lat = address1.getLatitude();
                            double lon = address1.getLongitude();
                            Toast.makeText(MapsActivity.this, lat + ", " + lon, Toast.LENGTH_SHORT).show();

                            LatLng dest = new LatLng(lat,lon);
                            mMap.addMarker(new MarkerOptions().position(dest).title(locationName.getText().toString()));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(dest));
                            mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
                        }
                        else
                        {
                            Toast.makeText(MapsActivity.this, "Nothing found!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace();
                        Toast.makeText(MapsActivity.this, "FAIL", Toast.LENGTH_SHORT).show();
                    }
                    //GeoApiContext context = new GeoApiContext();//"@string/google_maps_key"
                    //GeocodingResult[] results;
//                    try {
//                        System.out.println("before call");
//                        results = GeocodingApi.geocode(context,locationName.getText().toString()).await();
//                        double _x = results[0].geometry.location.lat;
//                        double _y = results[0].geometry.location.lng;
//                        //System.out.println(_x +","+_y);
//                        Toast.makeText(MapsActivity.this,_x +","+_y , Toast.LENGTH_SHORT).show();
//
//                        //DistanceMatrix results2 = notify(_x, _y, "Providence Amtrak");
//                        // System.out.println(results2.rows[0].elements[0].distance.inMeters);
//                        //System.out.println(results2.rows[0].elements[0].duration.inSeconds);
//
//
//                    } catch (Exception e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
                    _inputtext = locationName.getText().toString();
                    return true;

                }
                return false;
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void main(String[] args) {
        //Replace the API key below with a valid API key.


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
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng dest = new LatLng(_x, _y);
        mMap.addMarker(new MarkerOptions().position(dest).title("Destination"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(dest));
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Maps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.admin.ugh/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Maps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.admin.ugh/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
