package com.hayllieapps.digidiary.destinations;

import androidx.annotation.NonNull;
import com.hayllieapps.digidiary.AppNavGraphDirections;

public class DiaryEditFragmentDirections {
  private DiaryEditFragmentDirections() {
  }

  @NonNull
  public static AppNavGraphDirections.ActionToAddNewDiary actionToAddNewDiary() {
    return AppNavGraphDirections.actionToAddNewDiary();
  }
}
