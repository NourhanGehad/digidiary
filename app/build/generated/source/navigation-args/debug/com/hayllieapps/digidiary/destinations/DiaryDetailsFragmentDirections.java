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

public class DiaryDetailsFragmentDirections {
  private DiaryDetailsFragmentDirections() {
  }

  @NonNull
  public static ActionDetailsToEdit actionDetailsToEdit() {
    return new ActionDetailsToEdit();
  }

  @NonNull
  public static AppNavGraphDirections.ActionToAddNewDiary actionToAddNewDiary() {
    return AppNavGraphDirections.actionToAddNewDiary();
  }

  public static class ActionDetailsToEdit implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionDetailsToEdit() {
    }

    @NonNull
    public ActionDetailsToEdit setDiaryToEdit(@Nullable Diary diaryToEdit) {
      this.arguments.put("diary_to_edit", diaryToEdit);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("diary_to_edit")) {
        Diary diaryToEdit = (Diary) arguments.get("diary_to_edit");
        if (Parcelable.class.isAssignableFrom(Diary.class) || diaryToEdit == null) {
          __result.putParcelable("diary_to_edit", Parcelable.class.cast(diaryToEdit));
        } else if (Serializable.class.isAssignableFrom(Diary.class)) {
          __result.putSerializable("diary_to_edit", Serializable.class.cast(diaryToEdit));
        } else {
          throw new UnsupportedOperationException(Diary.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
        }
      } else {
        __result.putSerializable("diary_to_edit", null);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_details_to_edit;
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public Diary getDiaryToEdit() {
      return (Diary) arguments.get("diary_to_edit");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionDetailsToEdit that = (ActionDetailsToEdit) object;
      if (arguments.containsKey("diary_to_edit") != that.arguments.containsKey("diary_to_edit")) {
        return false;
      }
      if (getDiaryToEdit() != null ? !getDiaryToEdit().equals(that.getDiaryToEdit()) : that.getDiaryToEdit() != null) {
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
      result = 31 * result + (getDiaryToEdit() != null ? getDiaryToEdit().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionDetailsToEdit(actionId=" + getActionId() + "){"
          + "diaryToEdit=" + getDiaryToEdit()
          + "}";
    }
  }
}
