package com.example.alchemygame.LocationGenerator;

import android.content.Context;
import android.location.Location;
import com.example.alchemygame.LocationComponentActivity;
import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


//0.001 = 360ft lat
//0.001 = 309ft long
public class LocationGenerator {

    final double latMaxDistance = .005;
    final double lngMaxDistance = .006;

    final double latMinDistance = .001;
    final double lngMinDistance = .0015;

    private List<Location> points;

    public LocationGenerator(){
        points = new ArrayList<Location>();
    }

    public List<Location> GenerateLocations(int num, Location location){
        double lat = location.getLatitude();
        double lng = location.getLongitude();

        Random r = new Random();

        for(int i = 0; i < num; i++){
            double randLat = latMinDistance + (latMaxDistance - latMinDistance) * r.nextDouble();
            double randLng = lngMinDistance + (lngMaxDistance - lngMinDistance) * r.nextDouble();

            double finalLat = 0;
            double finalLng = 0;

            int k = r.nextInt(4);
            if(k <= 2){
                finalLat = lat + randLat;
            }else{
                finalLat = lat - randLat;
            }
            int t = r.nextInt(4);
            if(t <= 2){
                finalLng = lng + randLng;
            }else{
                finalLng = lng - randLng;
            }

            Location loc = new Location(location);
            loc.setLatitude(finalLat);
            loc.setLongitude(finalLng);

            points.add(loc);

        }
        return points;
    }

}
