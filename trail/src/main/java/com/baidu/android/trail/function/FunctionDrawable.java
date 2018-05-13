package com.baidu.android.trail.function;

import com.baidu.android.trail.R;

public enum FunctionDrawable {

  COMMUNICATION(R.id.communication, R.drawable.ic_communication_on, R.drawable.ic_communication_off),
  LIBRARY(R.id.library, R.drawable.ic_library_on, R.drawable.ic_library_off),
  FAVORITE(R.id.favorite, R.drawable.ic_favorite_on, R.drawable.ic_favorite_off),
  ERROR(R.id.error, R.drawable.ic_error_on, R.drawable.ic_error_off);

  private final int iconId;
  private final int activeResId;
  private final int defaultResId;

  FunctionDrawable(int iconId, int activeResId, int defaultResId) {
    this.iconId = iconId;
    this.activeResId = activeResId;
    this.defaultResId = defaultResId;
  }

  public int getActiveResId() {
    return activeResId;
  }

  public int getDefaultResId() {
    return defaultResId;
  }

  public int getIconId() {
    return iconId;
  }
}
