package com.example.icapa.guedr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class ForecastActivity extends AppCompatActivity {

    public static final String TAG = ForecastActivity.class.getCanonicalName();
    private static final int REQUEST_UNITS = 1;
    private static final String PREFERENCE_SHOW_CELSIUS = "showCelsius";

    private TextView mMaxTemp;
    private TextView mMinTemp;
    private TextView mHumidity;
    private TextView mDescription;
    private ImageView mForecastImage;

    private boolean mShowCelsius;
    private Forecast mForecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        mShowCelsius = prefs.getBoolean(PREFERENCE_SHOW_CELSIUS,true);


        setContentView(R.layout.activity_forecast);

        // Crear el modelo de pruebas
        mForecast = new Forecast(30,15,25,"Algunas nubes tímidas",R.drawable.ico_02);

        // Accedo a las vistas de mi interfaz
        mMaxTemp = (TextView) findViewById(R.id.max_temp);
        mMinTemp= (TextView) findViewById(R.id.min_temp);
        mHumidity=(TextView) findViewById(R.id.humidity);
        mDescription = (TextView) findViewById(R.id.forecast_description);
        mForecastImage = (ImageView) findViewById(R.id.forecast_image);

        // Actualizo la interfaz del modelo
        setForecast(mForecast);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean superReturn = super.onOptionsItemSelected(item);

        if (item.getItemId()==R.id.menu_show_settings){
            Log.v(TAG,"Se ha pulsado ajustes");
            Intent intent = new Intent(this,SettingsActivity.class);

            // Pasamos datos a la pantalla de ajustes
            if (mShowCelsius) {
                intent.putExtra(SettingsActivity.EXTRA_UNITS, R.id.celsius_rb);
            }else{
                intent.putExtra(SettingsActivity.EXTRA_UNITS, R.id.farenheit_rb);
            }

            // Lanzamos el Intent a Android
            startActivity(intent);
            startActivityForResult(intent, REQUEST_UNITS);

            return true;
        }

        return superReturn;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_settings,menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_UNITS) {

            if (resultCode == RESULT_OK) {
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
                PreferenceManager.getDefaultSharedPreferences(this)
                        .edit()
                        .putBoolean(PREFERENCE_SHOW_CELSIUS,mShowCelsius)
                        .apply();



                setForecast(mForecast);

            } else if (resultCode == RESULT_CANCELED) {

            }
        }
    }
/*
    @Override
    public void onClick(View view) {

        Log.v(TAG,"Botón pulsado");
        ImageView weatherImage = (ImageView) findViewById(R.id.imageWeather);
        switch (view.getId()) {
            case R.id.spanish_button:
                weatherImage.setImageResource(R.drawable.offline_weather2);
                break;
            case R.id.american_button:
                weatherImage.setImageResource(R.drawable.offline_weather);
                break;
            default:
                break;
        }


        //if (view.getId() == R.id.spanish_button){
        //    weatherImage.setImageResource(R.drawable.offline_weather2);
        //}else if (view.getId()==R.id.american_button){
        //    weatherImage.setImageResource(R.drawable.offline_weather);
        //}


        //if (view==change2AmericanButton) {
        //    weatherImage.setImageResource(R.drawable.offline_weather);
        //}else if (view==change2SpanishButton){
        //    weatherImage.setImageResource(R.drawable.offline_weather2);
        //}

    }
    */

}
/*
class MainClickHandler implements View.OnClickListener {
    private Activity context;
    public MainClickHandler(Activity context) {
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        Log.v(MainActivity.TAG,"Botón pulsado");
        ImageView weatherImage = (ImageView) context.findViewById(R.id.imageWeather);
        switch (view.getId()) {
            case R.id.spanish_button:
                weatherImage.setImageResource(R.drawable.offline_weather2);
                break;
            case R.id.american_button:
                weatherImage.setImageResource(R.drawable.offline_weather);
                break;
            default:
                break;
        }
    }
}
*/