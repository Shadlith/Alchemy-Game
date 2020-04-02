package com.example.alchemygame.ui.main;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.example.alchemygame.R;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment {

    private MapView mapView;
    private static final String ARG_SECTION_NUMBER = "section_number";
    private PageViewModel pageViewModel;

    public MapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        Log.d("MapFragment", "newInstance() called");
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MapFragment", "onCreate() called");
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
      }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("MapFragment", "onCreateView() called");
        View RootView = inflater.inflate(R.layout.fragment_main, container, false);
        return RootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("MapFragment", "onViewCreated() called");
        Mapbox.getInstance(getActivity().getBaseContext(), getString(R.string.mapbox_access_token));
        // MapView large
        mapView = (MapView) view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                    }
                });
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("MapFragment", "onResume() called");
        mapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("MapFragment", "onStart() called");
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("MapFragment", "onStop() called");
        mapView.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("MapFragment", "onPause() called");
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d("MapFragment", "onLowMemory() called");
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MapFragment", "onDestroy() called");
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("MapFragment", "onSaveInstanceState() called");
        mapView.onSaveInstanceState(outState);
    }


}
