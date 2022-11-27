package com.example.boardcar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



public class NearSiteUI extends AppCompatActivity
        implements OnMapReadyCallback {

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_site_ui);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        mMap = googleMap;

        LatLng DAEGU = new LatLng(35.87, 128.60);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(DAEGU);
        markerOptions.title("대구");
        markerOptions.snippet("근처 도시");
        mMap.addMarker(markerOptions);



        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DAEGU, 10));

    }

}