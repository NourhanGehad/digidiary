package com.hayllieapps.digidiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import com.hayllieapps.digidiary.DBHandler.SQLiteHelper;
import com.hayllieapps.digidiary.models.Diary;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Locale;

public class AppActivity extends AppCompatActivity {

    public static List<Diary> diaryList;
    public static List<Diary> favouriteDiariesList;
    public static SQLiteHelper sqLiteHelper;
    public static int diaryListSize = 0;
    public static int favouriteDiariesListSize = 0;

    protected IOnBackPressed onBackPressedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        String localeKey = "com.example.app.locale";
        SharedPreferences prefs2 = this.getSharedPreferences("com.example.app.digi-diary", Context.MODE_PRIVATE);
        String selectedLocale = prefs2.getString(localeKey, "");
        Locale newLocale = new Locale(selectedLocale);
        Locale.setDefault(newLocale);
        Configuration config = new Configuration();
        config.locale = newLocale;
        this.getResources().updateConfiguration(
                config,
                this.getResources().getDisplayMetrics()
        );

        setContentView(R.layout.activity_app);

        BottomNavigationView navView = findViewById(R.id.bottom_nav_bar);
        final NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navHostFragment.getNavController());


        FloatingActionButton compose_button = findViewById(R.id.compose_button_fab_app_a);
        compose_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navHostFragment.getNavController().navigate(R.id.action_to_add_new_diary);
               // Navigation.findNavController().navigate(R.id.action_splash_to_add_new_diary);
            }
        });



/*
// use a default value using new Date()
        String language;
        String locale = prefs.getString("locale", "en");*/
    }

    public void setOnBackPressedListener(IOnBackPressed onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    @Override
    public void onBackPressed() {
        if (onBackPressedListener != null){
            onBackPressedListener.onBackPressed();
        }
        else
            super.onBackPressed();
    }
}
