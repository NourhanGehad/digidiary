package com.hayllieapps.digidiary.destinations;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.hayllieapps.digidiary.AppNavGraphDirections;
import com.hayllieapps.digidiary.R;

public class SettingsFragmentDirections {
  private SettingsFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionSettingsToBin() {
    return new ActionOnlyNavDirections(R.id.action_settings_to_bin);
  }

  @NonNull
  public static NavDirections actionSettingsToLanguages() {
    return new ActionOnlyNavDirections(R.id.action_settings_to_languages);
  }

  @NonNull
  public static AppNavGraphDirections.ActionToAddNewDiary actionToAddNewDiary() {
    return AppNavGraphDirections.actionToAddNewDiary();
  }
}
