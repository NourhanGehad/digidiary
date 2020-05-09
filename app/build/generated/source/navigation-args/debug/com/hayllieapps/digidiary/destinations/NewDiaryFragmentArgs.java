package com.hayllieapps.digidiary.destinations;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavArgs;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class NewDiaryFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private NewDiaryFragmentArgs() {
  }

  private NewDiaryFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static NewDiaryFragmentArgs fromBundle(@NonNull Bundle bundle) {
    NewDiaryFragmentArgs __result = new NewDiaryFragmentArgs();
    bundle.setClassLoader(NewDiaryFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("selected_date")) {
      String selectedDate;
      selectedDate = bundle.getString("selected_date");
      __result.arguments.put("selected_date", selectedDate);
    } else {
      __result.arguments.put("selected_date", null);
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @Nullable
  public String getSelectedDate() {
    return (String) arguments.get("selected_date");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
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
  public boolean equals(Object object) {
    if (this == object) {
        return true;
    }
    if (object == null || getClass() != object.getClass()) {
        return false;
    }
    NewDiaryFragmentArgs that = (NewDiaryFragmentArgs) object;
    if (arguments.containsKey("selected_date") != that.arguments.containsKey("selected_date")) {
      return false;
    }
    if (getSelectedDate() != null ? !getSelectedDate().equals(that.getSelectedDate()) : that.getSelectedDate() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getSelectedDate() != null ? getSelectedDate().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "NewDiaryFragmentArgs{"
        + "selectedDate=" + getSelectedDate()
        + "}";
  }

  public static class Builder {
    private final HashMap arguments = new HashMap();

    public Builder(NewDiaryFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    public Builder() {
    }

    @NonNull
    public NewDiaryFragmentArgs build() {
      NewDiaryFragmentArgs result = new NewDiaryFragmentArgs(arguments);
      return result;
    }

    @NonNull
    public Builder setSelectedDate(@Nullable String selectedDate) {
      this.arguments.put("selected_date", selectedDate);
      return this;
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public String getSelectedDate() {
      return (String) arguments.get("selected_date");
    }
  }
}
