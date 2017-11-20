package com.github.anrimian.simpletestapppfour.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created on 16.02.2017.
 */

public class AndroidUtils {

    public static int getColorFromAttr(Context ctx, int attributeId) {
        int colorId = getResourceIdFromAttr(ctx, attributeId);
        return ContextCompat.getColor(ctx, colorId);
    }

    public static int dpToPx(int dp, Context ctx) {
        DisplayMetrics displayMetrics = ctx.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static Drawable getDrawableFromAttr(Context ctx, int attributeId) {
        int drawableId = getResourceIdFromAttr(ctx, attributeId);
        return ContextCompat.getDrawable(ctx, drawableId);
    }

    public static int getResourceIdFromAttr(Context ctx, int attributeId) {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = ctx.getTheme();
        theme.resolveAttribute(attributeId, typedValue, true);
        return typedValue.resourceId;
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }
}
