package com.example.icapa.guedr.activity;


import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.icapa.guedr.R;
import com.example.icapa.guedr.fragment.CitiPagerFragment;
import com.example.icapa.guedr.fragment.CityListFragment;

public class ForecastActivity extends AppCompatActivity {

    public static final String TAG = ForecastActivity.class.getCanonicalName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);



        // Accedemos a la toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Le decimoa a la pantalla q es nuestra action bar
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);

        // Cargamos a mano el fragment
        FragmentManager fm = getFragmentManager();

        // Comprobamos que no tenemos ya añadido el fragment a la jearquia
        if (fm.findFragmentById(R.id.forecast_fragment)==null){
            // No existe, lo añadimos con una transacción a la jerarquía de vistas
            fm.beginTransaction()
                    .add(R.id.forecast_fragment, new CityListFragment())
                    .commit();

        }

    }


}