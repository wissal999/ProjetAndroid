package com.example.mpdamproject2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_placeholder, new SettingsFragment()).commit();
        bottomNavigationView=(BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(naviglistener);
    }

    public BottomNavigationView.OnNavigationItemSelectedListener naviglistener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {

                case R.id.item_home:
                    Intent intentHome = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intentHome);
                    break;
                case R.id.item_cart:

                    break;
                case R.id.item_person:
                    Intent intentSettings = new Intent(getApplicationContext(), SettingsActivity.class);
                    startActivity(intentSettings);
                    break;



            }

            return true;
        }
    };

}