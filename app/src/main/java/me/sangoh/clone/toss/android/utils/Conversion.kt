package me.sangoh.clone.toss.android.utils

import android.content.Context
import android.util.TypedValue

fun dpToPx(context: Context, dp: Float): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        context.resources.displayMetrics
    ).toInt()
}

fun pxToDp(context: Context, px: Float): Float {
    var density = context.resources.displayMetrics.density

    density = when (density) {
        1.0f -> density * 4.0f
        1.5f -> density * (8 / 3.0f)
        2.0f -> density * 2.0f
        else -> density
    }

    return px / density
}


