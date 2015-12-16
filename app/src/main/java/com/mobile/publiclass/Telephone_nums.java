package com.mobile.publiclass;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

public class Telephone_nums extends TextView {
public Telephone_nums(Context con) {
  super(con);
}

public Telephone_nums(Context context, AttributeSet attrs) {
  super(context, attrs);
}
public Telephone_nums(Context context, AttributeSet attrs, int defStyle) {
  super(context, attrs, defStyle);
}
@Override
public boolean isFocused() {
return true;
}
@Override
protected void onFocusChanged(boolean focused, int direction,
   Rect previouslyFocusedRect) {
}
}