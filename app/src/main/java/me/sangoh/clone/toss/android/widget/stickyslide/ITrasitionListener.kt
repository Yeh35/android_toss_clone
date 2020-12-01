package me.sangoh.clone.toss.android.widget.stickyslide

import androidx.constraintlayout.motion.widget.MotionLayout

interface ITrasitionListener {

    /**
     * 동작이 끝나면 호출된다.
     */
    fun onTransitionCompleted(layout: MotionLayout, state: StickySlideState)

}