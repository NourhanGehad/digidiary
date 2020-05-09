package com.hayllieapps.digidiary.destinations;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.hayllieapps.digidiary.AppNavGraphDirections;
import com.hayllieapps.digidiary.R;

public class SplashFragmentDirections {
  private SplashFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionSplashToHome() {
    return new ActionOnlyNavDirections(R.id.action_splash_to_home);
  }

  @NonNull
  public static AppNavGraphDirections.ActionToAddNewDiary actionToAddNewDiary() {
    return AppNavGraphDirections.actionToAddNewDiary();
  }
}
