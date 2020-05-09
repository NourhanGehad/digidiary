package com.hayllieapps.digidiary.destinations;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hayllieapps.digidiary.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class LanguagesFragment extends Fragment {

    ListView lv_languages;

    public LanguagesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_languages, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomNavigationView navBar = getActivity().findViewById(R.id.bottom_nav_bar);
        navBar.setVisibility(View.GONE);


        final List<String> languagesList = new ArrayList<>
                (Arrays.asList(getResources().getStringArray(R.array.languages_array)));
        final SharedPreferences prefs = getActivity().getSharedPreferences(
                "com.example.app.digi-diary", Context.MODE_PRIVATE);


        lv_languages = view.findViewById(R.id.lv_languages);

        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(),R.layout.language_item,R.id.textView,languagesList);
        lv_languages.setAdapter(adapter);


        lv_languages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String selectedLocale = "en";
                switch(position){
                    case 0:{
                        selectedLocale = "en";
                        break;
                    }
                    case 1:{
                        selectedLocale = "ar";
                        break;
                    }
                    case 2:{
                        selectedLocale = "de";
                        break;
                    }
                    case 3:{
                        selectedLocale = "fr";
                        break;
                    }
                    case 4:{
                        selectedLocale = "es";
                        break;
                    }
                    case 5:{
                        selectedLocale = "so";
                        break;
                    }
                    case 6:{
                        selectedLocale = "ur";
                        break;
                    }
                }

                Locale newLocale = new Locale(selectedLocale);
                Locale.setDefault(newLocale);

                Configuration config = new Configuration();
                config.locale = newLocale;
                getActivity().getResources().updateConfiguration(
                        config,
                        getActivity().getResources().getDisplayMetrics()
                );
                String localeKey = "com.example.app.locale";
                prefs.edit().putString(localeKey, String.valueOf(newLocale)).apply();
                getActivity().recreate();
            }
        });



    }
}
