package com.mapbox.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.views.MapView;
import com.mapbox.utils.BubbleIconFactory;

public class MainActivity extends AppCompatActivity {

    private final static String LOG_TAG = "MainActivity";

    private final static String MAPBOX_ACCESS_TOKEN = "";

    private MapView mapView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Set up a standard Mapbox map
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setAccessToken(MAPBOX_ACCESS_TOKEN);
        mapView.setStyleUrl(Style.MAPBOX_STREETS);
        mapView.setLatLng(new LatLng(29.755136, -95.364454));
        mapView.setZoom(14);
        mapView.onCreate(savedInstanceState);

        // To place a BubbleIcon marker
        BubbleIconFactory bubbleIcon = new BubbleIconFactory(MainActivity.this);
        IconFactory iconFactory = IconFactory.getInstance(this);

        MarkerOptions markerOptions = new MarkerOptions()
                .icon(iconFactory.fromBitmap(bubbleIcon.makeIcon("$2,000")))
                .position(new LatLng(29.755136, -95.364454));
        mapView.addMarker(markerOptions);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause()  {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}
