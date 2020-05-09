package com.hayllieapps.digidiary.destinations;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavArgs;
import com.hayllieapps.digidiary.models.Diary;
import java.io.Serializable;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class DiaryEditFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private DiaryEditFragmentArgs() {
  }

  private DiaryEditFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static DiaryEditFragmentArgs fromBundle(@NonNull Bundle bundle) {
    DiaryEditFragmentArgs __result = new DiaryEditFragmentArgs();
    bundle.setClassLoader(DiaryEditFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("diary_to_edit")) {
      Diary diaryToEdit;
      if (Parcelable.class.isAssignableFrom(Diary.class) || Serializable.class.isAssignableFrom(Diary.class)) {
        diaryToEdit = (Diary) bundle.get("diary_to_edit");
      } else {
        throw new UnsupportedOperationException(Diary.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
      __result.arguments.put("diary_to_edit", diaryToEdit);
    } else {
      __result.arguments.put("diary_to_edit", null);
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @Nullable
  public Diary getDiaryToEdit() {
    return (Diary) arguments.get("diary_to_edit");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
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
  public boolean equals(Object object) {
    if (this == object) {
        return true;
    }
    if (object == null || getClass() != object.getClass()) {
        return false;
    }
    DiaryEditFragmentArgs that = (DiaryEditFragmentArgs) object;
    if (arguments.containsKey("diary_to_edit") != that.arguments.containsKey("diary_to_edit")) {
      return false;
    }
    if (getDiaryToEdit() != null ? !getDiaryToEdit().equals(that.getDiaryToEdit()) : that.getDiaryToEdit() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getDiaryToEdit() != null ? getDiaryToEdit().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "DiaryEditFragmentArgs{"
        + "diaryToEdit=" + getDiaryToEdit()
        + "}";
  }

  public static class Builder {
    private final HashMap arguments = new HashMap();

    public Builder(DiaryEditFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    public Builder() {
    }

    @NonNull
    public DiaryEditFragmentArgs build() {
      DiaryEditFragmentArgs result = new DiaryEditFragmentArgs(arguments);
      return result;
    }

    @NonNull
    public Builder setDiaryToEdit(@Nullable Diary diaryToEdit) {
      this.arguments.put("diary_to_edit", diaryToEdit);
      return this;
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public Diary getDiaryToEdit() {
      return (Diary) arguments.get("diary_to_edit");
    }
  }
}
