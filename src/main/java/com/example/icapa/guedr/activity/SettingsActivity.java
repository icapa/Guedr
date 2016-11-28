package com.example.icapa.guedr.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.example.icapa.guedr.R;

public class SettingsActivity extends AppCompatActivity {
    public static final String EXTRA_UNITS = "units";

    private RadioGroup mRadioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);



        //Accedemos a las vistas (radiogroup y botones)
        mRadioGroup = (RadioGroup) findViewById(R.id.units_rg);

        int radioSelected = getIntent().getIntExtra(SettingsActivity.EXTRA_UNITS,R.id.farenheit_rb);
        mRadioGroup.check(radioSelected);


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
        setResult(RESULT_CANCELED);
        finish();
    }

    private void acceptSetting() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(EXTRA_UNITS,mRadioGroup.getCheckedRadioButtonId());
        setResult(RESULT_OK,returnIntent);

        finish();
    }
}

