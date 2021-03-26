package com.example.weatheforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements CitiesFragment.CitiesFragmentListiner {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.rootView, new CitiesFragment()).commit();
    }


    @Override
    public void citiesToCurrent(String cityCountry) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView,CurrentWeatherFragment.newInstance(cityCountry))
                .addToBackStack(null)
                .commit();
    }
}