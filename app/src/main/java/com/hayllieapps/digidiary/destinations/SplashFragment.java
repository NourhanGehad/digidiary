package com.hayllieapps.digidiary.destinations;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hayllieapps.digidiary.DBHandler.SQLiteHelper;
import com.hayllieapps.digidiary.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;

import static com.hayllieapps.digidiary.AppActivity.sqLiteHelper;


public class SplashFragment extends Fragment {

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BottomNavigationView navBar = getActivity().findViewById(R.id.bottom_nav_bar);
        navBar.setVisibility(View.GONE);

        ConstraintLayout layout = getActivity().findViewById(R.id.splashContainer);
        AnimationDrawable animationDrawable = (AnimationDrawable) layout.getBackground();

        animationDrawable.setEnterFadeDuration(500);
        animationDrawable.setExitFadeDuration(500);

        animationDrawable.start();

        String localeKey = "com.example.app.locale";
        SharedPreferences prefs2 = getActivity().getSharedPreferences("com.example.app.digi-diary", Context.MODE_PRIVATE);
        String selectedLocale = prefs2.getString(localeKey, "en");
        Locale newLocale = new Locale(selectedLocale);
        Locale.setDefault(newLocale);
        Configuration config = new Configuration();
        config.locale = newLocale;
        getActivity().getResources().updateConfiguration(
                config,
                getActivity().getResources().getDisplayMetrics()
        );



        sqLiteHelper = new SQLiteHelper(getActivity(), "DiariesDB.sqlite", null, 1);
        try{
            sqLiteHelper.createTable();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    Navigation.findNavController(view).navigate(R.id.action_splash_to_home);
                }

            }, 1500); // 5000ms delay

        } catch (Exception ex){
            Toast.makeText(getActivity(), ex.toString(),Toast.LENGTH_SHORT).show();
        }
    }
}
