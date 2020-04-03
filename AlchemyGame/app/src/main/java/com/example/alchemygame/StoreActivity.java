package com.example.alchemygame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.alchemygame.Model.Database;
import com.example.alchemygame.ui.Inventory.InventoryPagerAdapter;
import com.example.alchemygame.ui.Inventory.ItemTypes.Cash;
import com.example.alchemygame.ui.Inventory.ItemTypes.Ingredient;
import com.example.alchemygame.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

public class StoreActivity extends AppCompatActivity {

    Database db;
    Button mBuy;
    TextView mValue, mType, mQuality;
    Cash cash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        db = new Database(this);
        for (int i =0; i < 50; i++) {
            Ingredient ingredient = new Ingredient();
            final String quality = ingredient.Quality;
            final String type = ingredient.Type;
            final String value = ingredient.Value;

            mValue = (TextView) findViewById(R.id.Store_value);
            mQuality = (TextView) findViewById(R.id.Store_quality);
            mType = (TextView) findViewById(R.id.Store_type);
            mBuy = (Button) findViewById(R.id.buy_button);
            mValue.setText(value);
            mQuality.setText(quality);
            mType.setText(type);

            mBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(StoreActivity.this);
                    builder.setMessage("Do you want to Buy this Ingredient?");
                    builder.setTitle("Alert!");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            db.addIngredients(type, quality, value);
                            cash = new Cash();
                            int value = cash.getCash();
                            value -= 5;
                            cash.updateCash(value);

                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }
            });
        }
    }
}
