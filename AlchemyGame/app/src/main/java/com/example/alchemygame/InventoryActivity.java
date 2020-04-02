package com.example.alchemygame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;


import com.example.alchemygame.Model.IngredientItem;
import com.example.alchemygame.ui.Inventory.*;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class InventoryActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        InventoryPagerAdapter sectionsPagerAdapter = new InventoryPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.inventory_fragment_viewer);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.inventory_tabs);
        tabs.setupWithViewPager(viewPager);




    }
}
