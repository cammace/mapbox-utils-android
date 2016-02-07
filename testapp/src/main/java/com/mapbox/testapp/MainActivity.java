package com.mapbox.testapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.views.MapView;
import com.mapbox.utils.BubbleIconFactory;
import com.mapbox.utils.MathUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static String LOG_TAG = "MainActivity";

    private final static String MAPBOX_ACCESS_TOKEN = "pk.eyJ1IjoiY2FtbWFjZSIsImEiOiI5OGQxZjRmZGQ2YjU3Mzk1YjJmZTQ5ZDY2MTg1NDJiOCJ9.hIFoCKGAGOwQkKyVPvrxvQ";

    private MapView mapView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Set up a standard Mapbox map
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setAccessToken(MAPBOX_ACCESS_TOKEN);
        mapView.setStyleUrl(Style.MAPBOX_STREETS);
        mapView.setLatLng(new LatLng(41.885, -87.679));
        mapView.setZoom(11);
        mapView.onCreate(savedInstanceState);

        /*
        // To place a BubbleIcon marker
        BubbleIconFactory bubbleIcon = new BubbleIconFactory(MainActivity.this);
        IconFactory iconFactory = IconFactory.getInstance(this);

        MarkerOptions markerOptions = new MarkerOptions()
                .icon(iconFactory.fromBitmap(bubbleIcon.makeIcon("HelloWorld")))
                .position(new LatLng(41.885, -87.679));
        mapView.addMarker(markerOptions);
        */


        MarkerOptions to = new MarkerOptions()
                .position(new LatLng(41.913046, -87.639444));
        mapView.addMarker(to);

        MarkerOptions from = new MarkerOptions()
                .position(new LatLng(41.890009, -87.762992));
        mapView.addMarker(from);

        LatLng[] point = new LatLng[2];
        point[0] = new LatLng(41.913046, -87.639444);
        point[1] = new LatLng(41.890009, -87.762992);

        mapView.addPolyline(new PolylineOptions()
                .add(point)
                .color(Color.parseColor("#3887be"))
                .width(5));

        List<LatLng> betweenPoints = MathUtil.pointsBetween(new LatLng(41.913046, -87.639444), new LatLng(41.890009, -87.762992), 10);

        for(int i =0; i<betweenPoints.size(); i++) {
            MarkerOptions betPoint = new MarkerOptions()
                    .position(betweenPoints.get(i));
            mapView.addMarker(betPoint);
        }
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
