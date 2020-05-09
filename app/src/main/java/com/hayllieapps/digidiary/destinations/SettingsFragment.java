package com.hayllieapps.digidiary.destinations;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;

import com.hayllieapps.digidiary.AppActivity;
import com.hayllieapps.digidiary.BaseBackPressedListener;
import com.hayllieapps.digidiary.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsFragment extends Fragment {

    TableRow bin;
    TableRow languages;
    TableRow share;
    TableRow rate;
    TableRow about;
    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppActivity)getActivity()).setOnBackPressedListener(new BaseBackPressedListener(getActivity()));
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BottomNavigationView navBar = getActivity().findViewById(R.id.bottom_nav_bar);
        navBar.setVisibility(View.VISIBLE);

        bin = view.findViewById(R.id.row_bin);
        languages = view.findViewById(R.id.row_languages);
        share = view.findViewById(R.id.row_share);
        rate = view.findViewById(R.id.row_rate);
        about = view.findViewById(R.id.row_about);

        bin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action =
                        SettingsFragmentDirections.actionSettingsToBin();
                Navigation.findNavController(view).navigate(action);
            }
        });
        languages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_settings_to_languages);

            }
        });
    }

}
