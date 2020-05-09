package com.hayllieapps.digidiary.destinations;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavDirections;
import com.hayllieapps.digidiary.AppNavGraphDirections;
import com.hayllieapps.digidiary.R;
import com.hayllieapps.digidiary.models.Diary;
import java.io.Serializable;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class HomeFragmentDirections {
  private HomeFragmentDirections() {
  }

  @NonNull
  public static ActionHomeToDetails actionHomeToDetails() {
    return new ActionHomeToDetails();
  }

  @NonNull
  public static AppNavGraphDirections.ActionToAddNewDiary actionToAddNewDiary() {
    return AppNavGraphDirections.actionToAddNewDiary();
  }

  public static class ActionHomeToDetails implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionHomeToDetails() {
    }

    @NonNull
    public ActionHomeToDetails setDiaryToDisplay(@Nullable Diary diaryToDisplay) {
      this.arguments.put("diary_to_display", diaryToDisplay);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("diary_to_display")) {
        Diary diaryToDisplay = (Diary) arguments.get("diary_to_display");
        if (Parcelable.class.isAssignableFrom(Diary.class) || diaryToDisplay == null) {
          __result.putParcelable("diary_to_display", Parcelable.class.cast(diaryToDisplay));
        } else if (Serializable.class.isAssignableFrom(Diary.class)) {
          __result.putSerializable("diary_to_display", Serializable.class.cast(diaryToDisplay));
        } else {
          throw new UnsupportedOperationException(Diary.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
        }
      } else {
        __result.putSerializable("diary_to_display", null);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_home_to_details;
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public Diary getDiaryToDisplay() {
      return (Diary) arguments.get("diary_to_display");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionHomeToDetails that = (ActionHomeToDetails) object;
      if (arguments.containsKey("diary_to_display") != that.arguments.containsKey("diary_to_display")) {
        return false;
      }
      if (getDiaryToDisplay() != null ? !getDiaryToDisplay().equals(that.getDiaryToDisplay()) : that.getDiaryToDisplay() != null) {
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
      result = 31 * result + (getDiaryToDisplay() != null ? getDiaryToDisplay().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionHomeToDetails(actionId=" + getActionId() + "){"
          + "diaryToDisplay=" + getDiaryToDisplay()
          + "}";
    }
  }
}
