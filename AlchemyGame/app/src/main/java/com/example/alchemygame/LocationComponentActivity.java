package com.example.alchemygame;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import android.widget.Toast;
import com.example.alchemygame.LocationGenerator.LocationGenerator;
import com.example.alchemygame.Model.Database;
import com.mapbox.android.core.location.*;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.GeoJson;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.annotation.*;
import com.mapbox.mapboxsdk.style.layers.CircleLayer;
import com.mapbox.mapboxsdk.style.layers.Layer;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import com.mapbox.mapboxsdk.utils.ColorUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Use the LocationComponent to easily add a device location "puck" to a Mapbox map.
 */
public class LocationComponentActivity extends AppCompatActivity implements
        OnMapReadyCallback, PermissionsListener {

        private static final long DEFAULT_INTERVAL_IN_MILLISECONDS = 1000L;
        private static final long DEFAULT_MAX_WAIT_TIME = DEFAULT_INTERVAL_IN_MILLISECONDS * 5;



        private MapboxMap mapboxMap;
        public MapView mapView;
        private PermissionsManager permissionsManager;
        private LocationEngine locationEngine;
        private LocationChangeListeningActivityLocationCallback callback =
                new LocationChangeListeningActivityLocationCallback(this);
        public Database db;
        public LocationGenerator lg;
        public List<Location> points;

        public Boolean styleLoaded;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            styleLoaded = false;

            lg = new LocationGenerator();
            // Mapbox access token is configured here. This needs to be called either in your application
            // object or in the same activity which contains the mapview.
            Mapbox.getInstance(this, getString(R.string.mapbox_access_token));
            points = new ArrayList<Location>();
            // This contains the MapView in XML and needs to be called after the access token is configured.
            setContentView(R.layout.activity_map);
            db = new Database(getApplicationContext());

            mapView = findViewById(R.id.mapView);
            mapView.onCreate(savedInstanceState);
            mapView.getMapAsync(this);
        }

    @Override
    public void onMapReady(@NonNull final MapboxMap mapboxMap) {
        this.mapboxMap = mapboxMap;

        mapboxMap.setStyle(Style.OUTDOORS,
                new Style.OnStyleLoaded() {
                    @Override public void onStyleLoaded(@NonNull Style style) {
                        enableLocationComponent(style);

                        if (db.getLocations().size() > 0) {
                            points = new ArrayList<>(db.getLocations().values());
                        } else {
                            points = lg.GenerateLocations(5, callback.getLocation());
                            for(int i = 0; i< points.size(); i++){
                                db.addLocation(points.get(i).getLatitude(), points.get(i).getLongitude());
                            }
                        }
                        final List<Feature> symbolLayerIconFeatureList = new ArrayList<>();
                        for(int i = 0; i< points.size(); i++){
                            symbolLayerIconFeatureList.add(Feature.fromGeometry(
                                    Point.fromLngLat(points.get(i).getLongitude(), points.get(i).getLatitude())));
                        }

                        style.addSource(new GeoJsonSource("SOURCE_ID",
                                FeatureCollection.fromFeatures(symbolLayerIconFeatureList)));
                        style.addLayer(new CircleLayer("LAYER_ID", "SOURCE_ID"));
                        styleLoaded = true;
                    }
                });
    }
/*
        @Override
        public void onMapReady(@NonNull final MapboxMap mapboxMap) {
            this.mapboxMap = mapboxMap;

            mapboxMap.setStyle(Style.OUTDOORS,
                    new Style.OnStyleLoaded() {
                        @Override public void onStyleLoaded(@NonNull Style style) {

                            circleManager = new CircleManager(mapView, mapboxMap, style);
                            enableLocationComponent(style);
                            LocationGenerator lg = new LocationGenerator();
                            List<Location> points = new ArrayList<Location>();

                            if(db.getLocations().size() > 0){
                                points = db.getLocations();
                            }else{
                                points = lg.GenerateLocations(5, callback.getLocation());
                            }
                            List<CircleOptions> circleOptionsList = new ArrayList<>();
                            for(int i = 0; i < points.size() -1; i++){
                                if(db.getLocations().size() > 0){
                                    db.addLocation(points.get(i).getLatitude(),points.get(i).getLatitude());
                                }

                                // random add circles across the globe
                                circleOptionsList.add(new CircleOptions()
                                        .withLatLng(new LatLng(points.get(i).getLatitude(), points.get(i).getLongitude()))
                                        .withCircleColor(ColorUtils.colorToRgbaString(255))
                                        .withCircleRadius(10000f)
                                );
                                Log.d("Location" + i, points.get(i).getLatitude() + " ,"+ points.get(i).getLongitude());

                            }
                            circleManager.create(circleOptionsList);
                            SymbolLayer sl = new SymbolLayer();
                        }
                    });




*/
        /**
         * Initialize the Maps SDK's LocationComponent
         */
        @SuppressWarnings( {"MissingPermission"})
        private void enableLocationComponent(@NonNull Style loadedMapStyle) {
            // Check if permissions are enabled and if not request
            if (PermissionsManager.areLocationPermissionsGranted(this)) {

                // Get an instance of the component
                LocationComponent locationComponent = mapboxMap.getLocationComponent();

                // Set the LocationComponent activation options
                LocationComponentActivationOptions locationComponentActivationOptions =
                        LocationComponentActivationOptions.builder(this, loadedMapStyle)
                                .useDefaultLocationEngine(false)
                                .build();

                // Activate with the LocationComponentActivationOptions object
                locationComponent.activateLocationComponent(locationComponentActivationOptions);

                // Enable to make component visible
                locationComponent.setLocationComponentEnabled(true);

                // Set the component's camera mode
                locationComponent.setCameraMode(CameraMode.TRACKING);

                // Set the component's render mode
                locationComponent.setRenderMode(RenderMode.COMPASS);

                initLocationEngine();
            } else {
                permissionsManager = new PermissionsManager(this);
                permissionsManager.requestLocationPermissions(this);
            }
        }

        /**
         * Set up the LocationEngine and the parameters for querying the device's location
         */
        @SuppressLint("MissingPermission")
        private void initLocationEngine() {
            locationEngine = LocationEngineProvider.getBestLocationEngine(this);

            LocationEngineRequest request = new LocationEngineRequest.Builder(DEFAULT_INTERVAL_IN_MILLISECONDS)
                    .setPriority(LocationEngineRequest.PRIORITY_HIGH_ACCURACY)
                    .setMaxWaitTime(DEFAULT_MAX_WAIT_TIME).build();

            locationEngine.requestLocationUpdates(request, callback, getMainLooper());
            locationEngine.getLastLocation(callback);

        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                               @NonNull int[] grantResults) {
            permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

        @Override
        public void onExplanationNeeded(List<String> permissionsToExplain) {
            Toast.makeText(this, R.string.user_location_permission_explanation,
                    Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPermissionResult(boolean granted) {
            if (granted) {
                mapboxMap.getStyle(new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        enableLocationComponent(style);
                    }
                });
            } else {
                Toast.makeText(this, R.string.user_location_permission_not_granted, Toast.LENGTH_LONG).show();
            }
        }

        private class LocationChangeListeningActivityLocationCallback
                implements LocationEngineCallback<LocationEngineResult> {

            final double latDistance = .0005;
            final double lngDistance = .0005;

            private final WeakReference<LocationComponentActivity> activityWeakReference;
            private Location CurrentLocation;


            LocationChangeListeningActivityLocationCallback(LocationComponentActivity activity) {
                this.activityWeakReference = new WeakReference<>(activity);
                this.CurrentLocation = new Location("");
            }

            /**
             * The LocationEngineCallback interface's method which fires when the device's location has changed.
             *
             * @param result the LocationEngineResult object which has the last known location within it.
             */
            @Override
            public void onSuccess(LocationEngineResult result) {
                LocationComponentActivity activity = activityWeakReference.get();

                if (activity != null) {
                    Location location = result.getLastLocation();
                    CurrentLocation = result.getLastLocation();
                    if (location == null) {
                        return;
                    }


                    // Pass the new location to the Maps SDK's LocationComponent
                    if (activity.mapboxMap != null && result.getLastLocation() != null) {
                        activity.mapboxMap.getLocationComponent().forceLocationUpdate(result.getLastLocation());
                    }

                    if(styleLoaded){
                        obtainItem();
                    }
                }
            }

            public Location getLocation(){
                return this.CurrentLocation;
            }

            /**
             * The LocationEngineCallback interface's method which fires when the device's location can't be captured
             *
             * @param exception the exception message
             */
            @Override
            public void onFailure(@NonNull Exception exception) {
                LocationComponentActivity activity = activityWeakReference.get();
                if (activity != null) {
                    Toast.makeText(activity, exception.getLocalizedMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            public void obtainItem(){
                Map<Integer, Location> locations = db.getLocations();
                Log.v("Database", String.valueOf(locations.size()));
                for (Map.Entry<Integer, Location> loc: locations.entrySet()) {
                    if(CurrentLocation.getLatitude() >  loc.getValue().getLatitude() -latDistance
                            && CurrentLocation.getLatitude() < loc.getValue().getLatitude() + latDistance
                            && CurrentLocation.getLongitude() > loc.getValue().getLongitude()-lngDistance
                            && CurrentLocation.getLongitude() < loc.getValue().getLongitude() + lngDistance){
                        db.deleteLocation(loc.getKey());
                        LocationGenerator lg2 = new LocationGenerator();
                        Location NewLocation = lg2.GenerateLocations(1, CurrentLocation).get(0);
                        db.addLocation(NewLocation.getLatitude(), NewLocation.getLongitude());
                        return;
                    }
                }
            }
        }

        @Override
        protected void onStart() {
            super.onStart();
            mapView.onStart();
        }

        @Override
        protected void onResume() {
            super.onResume();
            mapView.onResume();
        }

        @Override
        protected void onPause() {
            super.onPause();
            mapView.onPause();
        }

        @Override
        protected void onStop() {
            super.onStop();
            mapView.onStop();
        }

        @Override
        protected void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            mapView.onSaveInstanceState(outState);
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
// Prevent leaks
            if (locationEngine != null) {
                locationEngine.removeLocationUpdates(callback);
            }
            mapView.onDestroy();
        }

        @Override
        public void onLowMemory() {
            super.onLowMemory();
            mapView.onLowMemory();
        }
    }
