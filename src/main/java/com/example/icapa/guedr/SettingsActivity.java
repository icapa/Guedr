package com.example.icapa.guedr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {

    private RadioGroup mRadioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Accedemos a las vistas (radiogroup y botones)
        mRadioGroup = (RadioGroup) findViewById(R.id.units_rg);

        findViewById(R.id.accept_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acceptSetting();
            }
        });
        findViewById(R.id.cancel_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelSettings();
            }
        });
    }

    private void cancelSettings() {
    }

    private void acceptSetting() {
    }
}

