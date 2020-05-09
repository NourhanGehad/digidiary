package com.hayllieapps.digidiary;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavDirections;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class AppNavGraphDirections {
  private AppNavGraphDirections() {
  }

  @NonNull
  public static ActionToAddNewDiary actionToAddNewDiary() {
    return new ActionToAddNewDiary();
  }

  public static class ActionToAddNewDiary implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionToAddNewDiary() {
    }

    @NonNull
    public ActionToAddNewDiary setSelectedDate(@Nullable String selectedDate) {
      this.arguments.put("selected_date", selectedDate);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("selected_date")) {
        String selectedDate = (String) arguments.get("selected_date");
        __result.putString("selected_date", selectedDate);
      } else {
        __result.putString("selected_date", null);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_to_add_new_diary;
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public String getSelectedDate() {
      return (String) arguments.get("selected_date");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionToAddNewDiary that = (ActionToAddNewDiary) object;
      if (arguments.containsKey("selected_date") != that.arguments.containsKey("selected_date")) {
        return false;
      }
      if (getSelectedDate() != null ? !getSelectedDate().equals(that.getSelectedDate()) : that.getSelectedDate() != null) {
        return false;
      }
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (getSelectedDate() != null ? getSelectedDate().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionToAddNewDiary(actionId=" + getActionId() + "){"
          + "selectedDate=" + getSelectedDate()
          + "}";
    }
  }
}
