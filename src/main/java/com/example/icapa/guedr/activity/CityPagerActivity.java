package com.example.icapa.guedr.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.icapa.guedr.R;
import com.example.icapa.guedr.fragment.CitiPagerFragment;

/**
 * Created by icapa on 29/11/16.
 */

public class CityPagerActivity extends AppCompatActivity{

    public static final String EXTRA_CITY_INDEX = "EXTRA_CITY_INDEX";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_pager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentById(R.id.fragment_city_pager)==null){

            // Le paso la ciudad que quiere el usuario cargar
            int cityIndex = getIntent().getIntExtra(EXTRA_CITY_INDEX,0);

            // Creo el fragment con los argumentos
            CitiPagerFragment citiPagerFragment = CitiPagerFragment.newInstance(cityIndex);

            fm.beginTransaction()
                    .add(R.id.fragment_city_pager,citiPagerFragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean superValue =  super.onOptionsItemSelected(item);

        if (item.getItemId() == android.R.id.home){
            // Han pulsado flecha back, finalizamos actividad
            finish();
            return true;
        }

        return superValue;
    }
}
