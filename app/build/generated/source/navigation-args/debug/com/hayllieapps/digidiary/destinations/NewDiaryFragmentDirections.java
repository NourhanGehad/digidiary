package com.hayllieapps.digidiary.destinations;

import androidx.annotation.NonNull;
import com.hayllieapps.digidiary.AppNavGraphDirections;

public class NewDiaryFragmentDirections {
  private NewDiaryFragmentDirections() {
  }

  @NonNull
  public static AppNavGraphDirections.ActionToAddNewDiary actionToAddNewDiary() {
    return AppNavGraphDirections.actionToAddNewDiary();
  }
}
