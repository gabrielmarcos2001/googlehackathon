package com.codesmore.codesmore.ui.completeddetails;

import android.os.Bundle;
import android.view.View;

import com.codesmore.codesmore.BaseActivity;
import com.codesmore.codesmore.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class CompletedDetailsActivity extends BaseActivity implements
        View.OnClickListener, CompletedDetailsView, OnMapReadyCallback {

    private CompletedDetailsPresenter mPresenter;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_details);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        // Add a marker in Mountain View and move the camera
        LatLng mtnView = new LatLng(37.3861111, -122.0827778);
        mMap.addMarker(new MarkerOptions().position(mtnView).title("Marker in Mountain View"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mtnView));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(12));
    }

    @Override
    public void onClick(View v) {

    }
}
