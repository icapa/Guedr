package com.example.icapa.guedr.activity;


import android.app.FragmentManager;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;

import com.example.icapa.guedr.R;
import com.example.icapa.guedr.fragment.CitiPagerFragment;
import com.example.icapa.guedr.fragment.CityListFragment;
import com.example.icapa.guedr.model.City;

public class ForecastActivity extends AppCompatActivity implements CityListFragment.OnCitySelectedListener{

    public static final String TAG = ForecastActivity.class.getCanonicalName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        // COsillas utiles
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        int dpWidth = (int) (width/metrics.density);
        int dpHeight = (int) (height/metrics.density);
        String model = Build.MODEL;


        // Accedemos a la toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Le decimoa a la pantalla q es nuestra action bar
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);

        // Cargamos a mano el fragment
        FragmentManager fm = getFragmentManager();

        // Preguntamos a ver si existe el hueco para el CityPager
        if (findViewById(R.id.fragment_city_pager) != null){
            // Existe el huevo, metemos el fargment del cityPager
            if (fm.findFragmentById(R.id.fragment_city_pager) == null){
                fm.beginTransaction()
                        .add(R.id.fragment_city_pager, new CitiPagerFragment())
                        .commit();
            }
        }
        // Preguntamos si exite el hueco para el CityList
        if (findViewById(R.id.fragment_city_list) != null){
            // Existe hueco metemos el Frament city list
            if (fm.findFragmentById(R.id.fragment_city_list)==null){
                fm.beginTransaction()
                        .add(R.id.fragment_city_list,new CityListFragment())
                        .commit();
            }
        }

    }


    @Override
    public void onCitySelected(City city, int position) {
        Log.v("ForecastActivity","Se ha seleccionado la ciudad " + position);

        FragmentManager fm = getFragmentManager();
        CitiPagerFragment citiPagerFragment = (CitiPagerFragment) fm.findFragmentById(R.id.fragment_city_pager);
        if (citiPagerFragment != null){
            // Tenemos un pager le decimos q se mueva a otra ciudad
            citiPagerFragment.showCity(position);
        }else{
            Intent intent = new Intent(this,CityPagerActivity.class);
            // Le pasamos la ciudad inicial a la actividad siguiente
            intent.putExtra(CityPagerActivity.EXTRA_CITY_INDEX,position);
            startActivity(intent);
        }




    }
}