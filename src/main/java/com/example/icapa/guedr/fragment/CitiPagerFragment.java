package com.example.icapa.guedr.fragment;


import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.icapa.guedr.R;
import com.example.icapa.guedr.model.Cities;
import com.example.icapa.guedr.model.City;

/**
 * A simple {@link Fragment} subclass.
 */
public class CitiPagerFragment extends Fragment {


    public CitiPagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_citi_pager, container, false);

        ViewPager pager = (ViewPager) root.findViewById(R.id.view_pager);
        pager.setAdapter(new CityPagerAdapter(getFragmentManager()));


        return root;
    }

}

class CityPagerAdapter extends FragmentPagerAdapter {

    private Cities mCities;

    public CityPagerAdapter(FragmentManager fm) {
        super(fm);
        mCities = new Cities();
    }

    @Override
    public Fragment getItem(int position) {
        City city = mCities.getCities().get(position);
        ForecastFragment forecastFragment = ForecastFragment.newInstances(city);
        return forecastFragment;
    }

    @Override
    public int getCount() {
        return mCities.getCount();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        super.getPageTitle(position);
        City city = mCities.getCities().get(position);
        return city.getName();
    }
}
