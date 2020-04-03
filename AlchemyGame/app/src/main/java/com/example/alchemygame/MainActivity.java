package com.example.alchemygame;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mapbox.mapboxsdk.Mapbox;


public class MainActivity extends AppCompatActivity {

    private Button mMapButton;
    private Button mInventoryButton;
    private Button mStatsButton;
    private Button mStoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Mapbox access token is configured here. This needs to be called either in your application
        // object or in the same activity which contains the mapview.
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));

        mMapButton = findViewById(R.id.mapButton);
        mMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent map = new Intent(MainActivity.this, LocationComponentActivity.class);
                startActivity(map);
            }
        });


        mInventoryButton = findViewById(R.id.inventoryButton);
        mInventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shop = new Intent(MainActivity.this, InventoryActivity.class);
                startActivity(shop);
            }
        });

        mStatsButton = findViewById(R.id.statsButton);
        mStatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stats = new Intent(MainActivity.this, StatsActivity.class);
                startActivity(stats);
            }
        });

        mStoreButton = findViewById(R.id.storeButton);
        mStoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent store = new Intent(MainActivity.this, StoreActivity.class);
                startActivity(store);
            }
        });
    }

    public void openInventory(View view) {
        Intent intent = new Intent(this, InventoryActivity.class);
        startActivity(intent);
    }




}
