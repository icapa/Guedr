package com.example.icapa.guedr.fragment;


import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.icapa.guedr.R;
import com.example.icapa.guedr.model.Cities;
import com.example.icapa.guedr.model.City;

/**
 * A simple {@link Fragment} subclass.
 */
public class CitiPagerFragment extends Fragment {

    private static final String ARG_CITY_INDEX="ARG_CITY_INDEX";

    private Cities mCities;
    private ViewPager mPager;
    private int mInitialCityIndex;

    public static CitiPagerFragment newInstance(int cityIndex){
        Bundle arguments = new Bundle();
        arguments.putInt(ARG_CITY_INDEX,cityIndex);
        CitiPagerFragment citiPagerFragment = new CitiPagerFragment();
        citiPagerFragment.setArguments(arguments);
        return citiPagerFragment;
    }

    public CitiPagerFragment() {
        // Required empty public constructor
        mCities = new Cities();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
            mInitialCityIndex = getArguments().getInt(ARG_CITY_INDEX,0);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_citi_pager, container, false);

        mPager = (ViewPager) root.findViewById(R.id.view_pager);
        mPager.setAdapter(new CityPagerAdapter(getFragmentManager(),mCities));

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updateCityInfo(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //updateCityInfo(mInitialCityIndex);
        mPager.setCurrentItem(mInitialCityIndex);
        updateCityInfo(mInitialCityIndex);
        return root;
    }

    private void updateCityInfo(int position) {
        if (getActivity() instanceof AppCompatActivity){
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            ActionBar actionBar = activity.getSupportActionBar();
            if (actionBar != null){
                actionBar.setTitle(mCities.getCity(position).getName());
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_pager,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean superValue = super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.previous) {
            mPager.setCurrentItem(mPager.getCurrentItem()-1);
            return true;
        }
        else if(item.getItemId() == R.id.next){
            mPager.setCurrentItem(mPager.getCurrentItem()+1);
            return true;
        }
        return superValue;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem menPrev = menu.findItem(R.id.previous);
        MenuItem menNext = menu.findItem(R.id.next);
        menPrev.setEnabled(mPager.getCurrentItem()>0);
        menNext.setEnabled(mPager.getCurrentItem()< mCities.getCount()-1);
    }
}

class CityPagerAdapter extends FragmentPagerAdapter {

    private Cities mCities;

    public CityPagerAdapter(FragmentManager fm, Cities cities) {
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
