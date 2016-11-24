package com.example.icapa.guedr;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ForecastActivity extends AppCompatActivity {

    public static final String TAG = ForecastActivity.class.getCanonicalName();

    private TextView mMaxTemp;
    private TextView mMinTemp;
    private TextView mHumidity;
    private TextView mDescription;
    private ImageView mForecastImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_forecast);

        // Crear el modelo de pruebas
        Forecast forecast = new Forecast(30,15,25,"Algunas nubes tímidas",R.drawable.ico_02);

        // Accedo a las vistas de mi interfaz
        mMaxTemp = (TextView) findViewById(R.id.max_temp);
        mMinTemp= (TextView) findViewById(R.id.min_temp);
        mHumidity=(TextView) findViewById(R.id.humidity);
        mDescription = (TextView) findViewById(R.id.forecast_description);
        mForecastImage = (ImageView) findViewById(R.id.forecast_image);

        // Actualizo la interfaz del modelo
        setForecast(forecast);







    }

    private void setForecast(Forecast forecast) {
        mMaxTemp.setText(String.format(getString(R.string.max_temp_format),forecast.getMaxTemp()));
        mMinTemp.setText(String.format(getString(R.string.min_temp_format),forecast.getMinTemp()));
        mHumidity.setText(String.format(getString(R.string.humidity_format),forecast.getHumidity()));
        mDescription.setText(forecast.getDescription());
        mForecastImage.setImageResource(forecast.getIcon());
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