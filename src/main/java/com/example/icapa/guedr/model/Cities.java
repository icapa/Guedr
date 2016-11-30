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
        mCities.add(new City("Santander"));
        mCities.add(new City("Madrid"));
        mCities.add(new City("Quito"));

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
