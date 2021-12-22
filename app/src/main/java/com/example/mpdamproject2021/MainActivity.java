package com.example.mpdamproject2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView=(BottomNavigationView) findViewById(R.id.btn_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(naviglistener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_placeholder,new HomeFragment()).commit();

    }
    public BottomNavigationView.OnNavigationItemSelectedListener naviglistener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment unFragment=null;
            switch (item.getItemId()){

                case R.id.item_home:
                    unFragment=new HomeFragment();
                    break;
                case R.id.item_cart:
                    unFragment=new CartFragment();
                    break;
                case R.id.item_person:
                    Intent intent1=new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.item_scan:
                    Intent intent=new Intent(getApplicationContext(),ScanActivity.class);
                    startActivity(intent);
                    break;



            }

            String URL="http://192.168.21.58:80/";
            Bundle bundle=new Bundle();
            bundle.putString("url",URL);
            unFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_placeholder,unFragment).commit();
            return true;
        }
    };

}