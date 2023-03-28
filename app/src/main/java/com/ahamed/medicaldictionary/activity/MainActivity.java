package com.ahamed.medicaldictionary.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ahamed.medicaldictionary.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        BottomNavigationView bottomNavId = findViewById(R.id.bottomNavigationView);
        assert navHostFragment != null;
        NavigationUI.setupWithNavController(bottomNavId, navHostFragment.getNavController());*/


    }
}