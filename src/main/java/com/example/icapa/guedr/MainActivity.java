package com.example.icapa.guedr;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getCanonicalName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Crear el manejador
        //MainClickHandler mainClickHandler = new MainClickHandler(this);
        // Final es de solo lectura, para evitar que se modifique
        final ToggleButton changeWeatherSystemButton = (ToggleButton) findViewById(R.id.change_weather_button);


        changeWeatherSystemButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                changeWeatherSystem(changeWeatherSystemButton.isChecked());
            }
        });

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
    public void changeWeatherSystem(boolean americanSystem){
        ImageView weatherImage = (ImageView) findViewById(R.id.imageWeather);
        if (americanSystem){
            weatherImage.setImageResource(R.drawable.offline_weather);
        }else{
            weatherImage.setImageResource(R.drawable.offline_weather2);
        }
    }
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