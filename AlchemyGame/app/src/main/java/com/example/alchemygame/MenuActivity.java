package com.example.alchemygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    private Button mMapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        /*mMapButton = findViewById(R.id.mapButton);
        mMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //placeholder to check
                Toast.makeText(MenuActivity.this, "Test", Toast.LENGTH_SHORT).show();

            }
        });*/
    }

    public void openMap(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
