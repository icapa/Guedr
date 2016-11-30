package com.example.icapa.guedr.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.icapa.guedr.R;
import com.example.icapa.guedr.model.Cities;
import com.example.icapa.guedr.model.City;

/**
 * Created by icapa on 29/11/16.
 */

public class CityListFragment extends Fragment {

    private OnCitySelectedListener mOnCitySelectedListener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_city_list,container,false);

        // Accedemos al listView
        ListView list = (ListView) root.findViewById(android.R.id.list);
        // Creamos nuestro modelo
        final Cities cities = new Cities();
        // Creamos un adaptador para poner en común
        ArrayAdapter<City> adapter = new ArrayAdapter<City>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                cities.getCities()
        );
        // Le asignamos el adaptador a la lista
        list.setAdapter(adapter);
        // Enterarse cuando se ha pulsado una fila
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Avisamos al listener que el usuario ha pulsado una fila
                if (mOnCitySelectedListener!=null) {
                    mOnCitySelectedListener.onCitySelected(cities.getCity(position), position);
                }
            }
        });

        return root;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (getActivity() instanceof OnCitySelectedListener){
            mOnCitySelectedListener = (OnCitySelectedListener) getActivity();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof OnCitySelectedListener){
            mOnCitySelectedListener = (OnCitySelectedListener) getActivity();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnCitySelectedListener = null;
    }

    public interface OnCitySelectedListener {
        void onCitySelected(City city, int position);
    }
}
