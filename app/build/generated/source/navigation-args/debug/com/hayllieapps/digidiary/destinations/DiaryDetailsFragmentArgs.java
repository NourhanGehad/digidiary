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

public class DiaryDetailsFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private DiaryDetailsFragmentArgs() {
  }

  private DiaryDetailsFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static DiaryDetailsFragmentArgs fromBundle(@NonNull Bundle bundle) {
    DiaryDetailsFragmentArgs __result = new DiaryDetailsFragmentArgs();
    bundle.setClassLoader(DiaryDetailsFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("diary_to_display")) {
      Diary diaryToDisplay;
      if (Parcelable.class.isAssignableFrom(Diary.class) || Serializable.class.isAssignableFrom(Diary.class)) {
        diaryToDisplay = (Diary) bundle.get("diary_to_display");
      } else {
        throw new UnsupportedOperationException(Diary.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
      __result.arguments.put("diary_to_display", diaryToDisplay);
    } else {
      __result.arguments.put("diary_to_display", null);
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @Nullable
  public Diary getDiaryToDisplay() {
    return (Diary) arguments.get("diary_to_display");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
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
  public boolean equals(Object object) {
    if (this == object) {
        return true;
    }
    if (object == null || getClass() != object.getClass()) {
        return false;
    }
    DiaryDetailsFragmentArgs that = (DiaryDetailsFragmentArgs) object;
    if (arguments.containsKey("diary_to_display") != that.arguments.containsKey("diary_to_display")) {
      return false;
    }
    if (getDiaryToDisplay() != null ? !getDiaryToDisplay().equals(that.getDiaryToDisplay()) : that.getDiaryToDisplay() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getDiaryToDisplay() != null ? getDiaryToDisplay().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "DiaryDetailsFragmentArgs{"
        + "diaryToDisplay=" + getDiaryToDisplay()
        + "}";
  }

  public static class Builder {
    private final HashMap arguments = new HashMap();

    public Builder(DiaryDetailsFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    public Builder() {
    }

    @NonNull
    public DiaryDetailsFragmentArgs build() {
      DiaryDetailsFragmentArgs result = new DiaryDetailsFragmentArgs(arguments);
      return result;
    }

    @NonNull
    public Builder setDiaryToDisplay(@Nullable Diary diaryToDisplay) {
      this.arguments.put("diary_to_display", diaryToDisplay);
      return this;
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public Diary getDiaryToDisplay() {
      return (Diary) arguments.get("diary_to_display");
    }
  }
}
