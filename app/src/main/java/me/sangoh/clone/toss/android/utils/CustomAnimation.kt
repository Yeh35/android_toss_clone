package me.sangoh.clone.toss.android.utils

import android.animation.ObjectAnimator
import android.view.View

fun animationAppearWhileComingUp(view: View) {

    ObjectAnimator.ofFloat(view, View.ALPHA, 0F).apply {
        startDelay = 0
        duration = 0
    }.start()

    // 천천히 올라오면서 나타나기
    ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, 50f, 0f).apply {
        duration = 1000
    }.start()

    ObjectAnimator.ofFloat(view, View.ALPHA, 1F).apply {
        startDelay = 500
        duration = 1000
    }.start()
}

fun animationRotateSidewaysAndHighlight(view: View) {

    ObjectAnimator.ofFloat(view, View.ROTATION_Y, -360f, 0f).apply {
        startDelay = 0
        duration = 500
    }.start()
}