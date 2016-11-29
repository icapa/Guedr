package com.example.icapa.guedr.model;

import com.example.icapa.guedr.R;

import java.util.LinkedList;

/**
 * Created by icapa on 28/11/16.
 */

public class Cities {
    private LinkedList<City> mCities;

    public Cities() {
        mCities = new LinkedList<>();
        mCities.add(new City("Santander",new Forecast(20,8,80,"Soleado con nubes", R.drawable.ico_02)));
        mCities.add(new City("Madrid",new Forecast(25,23,19,"Sol a tope",R.drawable.ico_01)));
        mCities.add(new City("Quito",new Forecast(30,15,40,"Arcoiris",R.drawable.ico_10)));

    }

    public LinkedList<City> getCities() {
        return mCities;
    }
    public int getCount(){
        return mCities.size();
    }
    public City getCity (int position){
        return mCities.get(position);
    }
}
