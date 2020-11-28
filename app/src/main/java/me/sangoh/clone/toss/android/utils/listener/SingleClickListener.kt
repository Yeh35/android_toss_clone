package me.sangoh.clone.toss.android.utils.listener

import android.os.SystemClock
import android.view.View

/**
 * 여러번 클릭 방지용 listener
 */
abstract class SingleClickListener : View.OnClickListener {

    private var defaultInterval = 0
    private var lastTimeClicked: Long = 0

    abstract fun performClick(v: View?)

    constructor() : this(1000)

    constructor(minInterval: Int) {
        defaultInterval = minInterval
    }

    override fun onClick(v: View?) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        performClick(v)
    }


}