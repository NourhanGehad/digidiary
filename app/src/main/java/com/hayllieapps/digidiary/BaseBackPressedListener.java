package com.hayllieapps.digidiary;

import androidx.fragment.app.FragmentActivity;

public class BaseBackPressedListener implements IOnBackPressed {
    private final FragmentActivity activity;

    public BaseBackPressedListener(FragmentActivity activity) {
        this.activity = activity;
    }


    @Override
    public void onBackPressed() {
        activity.finish();
    }
}