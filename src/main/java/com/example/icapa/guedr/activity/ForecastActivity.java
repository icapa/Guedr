package com.example.icapa.guedr.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.icapa.guedr.R;

public class ForecastActivity extends AppCompatActivity {

    public static final String TAG = ForecastActivity.class.getCanonicalName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        // Accedemos a la toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Le decimoa a la pantalla q es nuestra action bar
        setSupportActionBar(toolbar);

    }


}