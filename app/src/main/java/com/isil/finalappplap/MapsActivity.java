package com.isil.finalappplap;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.isil.finalappplap.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    String mensaje;
    Double latitud, longitud;

    private GoogleMap mMap;
    private ActivityMapsBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        latitud = Double.valueOf(getIntent().getStringExtra("latitud"));
        longitud = Double.valueOf(getIntent().getStringExtra("longitud"));
        mensaje = getIntent().getStringExtra("mensaje");
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

        LatLng coordenadas = new LatLng(latitud, longitud);
        mMap.addMarker(new MarkerOptions().position(coordenadas).title(mensaje));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordenadas,11));
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();

        Circle circle1 = mMap.addCircle(new CircleOptions()
                .center(new LatLng(latitud, longitud))
                .radius(3000)    //metros
                .strokeColor(Color.RED));

    }
}