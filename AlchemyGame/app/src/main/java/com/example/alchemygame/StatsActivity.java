package com.example.alchemygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.alchemygame.ui.Inventory.ItemTypes.Cash;


public class StatsActivity extends AppCompatActivity {

    public Cash cash;
    private Button mBackButton;
    TextView cashView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        cash = new Cash();
        int value = cash.getCash();
        cashView = (TextView) findViewById(R.id.Cash_value);
        cashView.setText(String.valueOf(value));

        mBackButton = (Button) findViewById(R.id.back_button);
    }

}
