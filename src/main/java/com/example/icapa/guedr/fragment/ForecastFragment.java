package com.example.icapa.guedr.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.icapa.guedr.R;
import com.example.icapa.guedr.activity.SettingsActivity;
import com.example.icapa.guedr.model.City;
import com.example.icapa.guedr.model.Forecast;

/**
 * Created by icapa on 28/11/16.
 */

public class ForecastFragment extends Fragment {
    public static final String TAG = ForecastFragment.class.getCanonicalName();
    private static final int REQUEST_UNITS = 1;
    private static final String PREFERENCE_SHOW_CELSIUS = "showCelsius";
    private static final String ARG_CITY = "city";

    private TextView mMaxTemp;
    private TextView mMinTemp;
    private TextView mHumidity;
    private TextView mDescription;
    private ImageView mForecastImage;

    private TextView mCityTextView;

    private boolean mShowCelsius;
    private Forecast mForecast;

    private City mCity;

    public static ForecastFragment newInstances(City city){
        ForecastFragment forecastFragment = new ForecastFragment();
        Bundle arguments = new Bundle();
        arguments.putSerializable(ARG_CITY,city);
        forecastFragment.setArguments(arguments);
        return forecastFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null){
            mCity = (City) getArguments().getSerializable(ARG_CITY);
        }


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_forecast,container,false);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        mShowCelsius = prefs.getBoolean(PREFERENCE_SHOW_CELSIUS,true);

        // Accedo a las vistas de mi interfaz
        mMaxTemp = (TextView) root.findViewById(R.id.max_temp);
        mMinTemp= (TextView) root.findViewById(R.id.min_temp);
        mHumidity=(TextView) root.findViewById(R.id.humidity);
        mDescription = (TextView) root.findViewById(R.id.forecast_description);
        mForecastImage = (ImageView) root.findViewById(R.id.forecast_image);
        mCityTextView = (TextView) root.findViewById(R.id.city);

        mCityTextView.setText(mCity.getName());
        // Actualizo la interfaz del modelo
        setForecast(mCity.getForecast());
        return root;

    }
    private void setForecast(Forecast forecast) {
        float maxTemp = forecast.getMaxTemp();
        float minTemp = forecast.getMinTemp();
        String units = getString(R.string.units_celsius);


        if (!mShowCelsius){
            maxTemp = toFahrenheit(maxTemp);
            minTemp = toFahrenheit(minTemp);
            units = getString(R.string.units_fah);
        }

        mMaxTemp.setText(String.format(getString(R.string.max_temp_format),maxTemp,units));
        mMinTemp.setText(String.format(getString(R.string.min_temp_format),minTemp,units));
        mHumidity.setText(String.format(getString(R.string.humidity_format),forecast.getHumidity()));
        mDescription.setText(forecast.getDescription());
        mForecastImage.setImageResource(forecast.getIcon());


    }

    private float toFahrenheit(float celsius) {
        return (celsius * 1.8f) +32;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_settings,menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        boolean superReturn = super.onOptionsItemSelected(item);

        if (item.getItemId()==R.id.menu_show_settings){
            Log.v(TAG,"Se ha pulsado ajustes");
            Intent intent = new Intent(getActivity(),SettingsActivity.class);

            // Pasamos datos a la pantalla de ajustes
            if (mShowCelsius) {
                intent.putExtra(SettingsActivity.EXTRA_UNITS, R.id.celsius_rb);
            }else{
                intent.putExtra(SettingsActivity.EXTRA_UNITS, R.id.farenheit_rb);
            }

            // Lanzamos el Intent a Android

            startActivityForResult(intent, REQUEST_UNITS);

            return true;
        }

        return superReturn;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_UNITS) {
            final boolean oldShowCelsius = mShowCelsius;

            if (resultCode == Activity.RESULT_OK) {
                int optionSelected = data.getIntExtra(SettingsActivity.EXTRA_UNITS, R.id.farenheit_rb);
                if (optionSelected == R.id.farenheit_rb) {
                    Log.v(TAG, "Cambiamos a fah");
                    mShowCelsius = false;
                } else if (optionSelected == R.id.celsius_rb) {
                    Log.v(TAG, "Cambiamos a cel");
                    mShowCelsius = true;
                }

//                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//                SharedPreferences.Editor editor = prefs.edit();
//                editor.putBoolean("showCelsius",mShowCelsius);
//                editor.apply();
                PreferenceManager.getDefaultSharedPreferences(getActivity())
                        .edit()
                        .putBoolean(PREFERENCE_SHOW_CELSIUS,mShowCelsius)
                        .apply();



                setForecast(mForecast);

                if (mShowCelsius != oldShowCelsius) {
                    // Indicamos al usuario que se han actualizado los ajustes
                    //Toast.makeText(this, R.string.preferencias_actualizadas,Toast.LENGTH_LONG).show();

                    if (getView()!=null) {
                        Snackbar.make(getView(), R.string.preferencias_actualizadas, Snackbar.LENGTH_LONG)
                                .setAction(R.string.undo, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        mShowCelsius = oldShowCelsius;
                                        PreferenceManager.getDefaultSharedPreferences(getActivity())
                                                .edit()
                                                .putBoolean(PREFERENCE_SHOW_CELSIUS, mShowCelsius)
                                                .apply();
                                        setForecast(mForecast);

                                    }


                                })
                                .show();
                    }
                }


            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.v(TAG,"Anulado");
            }
        }
    }
}
