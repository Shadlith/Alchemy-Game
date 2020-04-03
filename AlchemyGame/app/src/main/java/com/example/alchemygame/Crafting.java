package com.example.alchemygame;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.example.alchemygame.Model.Database;
import com.example.alchemygame.Model.IngredientItem;
import com.example.alchemygame.ui.Inventory.InventoryPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Crafting extends AppCompatActivity {


    Button CraftMoney;
    Button CraftLocation;

    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the activity layout xml file.
        setContentView(R.layout.activity_crafting);

        db = new Database(getApplicationContext());

        CraftMoney = findViewById(R.id.craftButton);
        CraftMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<IngredientItem> arrayList = db.getIngredients();
                ArrayList<IngredientItem> itemsToRemove = new ArrayList<IngredientItem>();
                int numberOfOre = 0;
                for (IngredientItem item: arrayList) {
                    if(item.getType().equals("Ore") && numberOfOre < 3){
                        numberOfOre++;
                        itemsToRemove.add(item);
                    }
                }
                if(numberOfOre >= 3){
                    db.addPotions("MoneyPotion", "+300g", "3 Ore");
                    db.deleteIngredient(itemsToRemove.get(0).getIDValue());
                    db.deleteIngredient(itemsToRemove.get(1).getIDValue());
                    db.deleteIngredient(itemsToRemove.get(2).getIDValue());
                }
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }

}
