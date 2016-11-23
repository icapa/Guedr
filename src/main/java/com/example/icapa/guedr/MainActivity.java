package com.example.icapa.guedr;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button changeSystemButtom = (Button) findViewById(R.id.button_change_system);
        changeSystemButtom.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.v(TAG,"Botón pulsado");
        ImageView weatherImage = (ImageView) findViewById(R.id.imageWeather);
        weatherImage.setImageResource(R.drawable.offline_weather2);

    }
}
