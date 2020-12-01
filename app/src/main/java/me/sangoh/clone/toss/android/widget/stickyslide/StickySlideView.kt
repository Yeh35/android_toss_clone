package me.sangoh.clone.toss.android.widget.stickyslide

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.constraintlayout.motion.widget.MotionLayout
import me.sangeoh.clone.toss.android.R

open class StickySlideView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val motionLayout: MotionLayout
    protected val baseLayout: FrameLayout

    init {
        val li = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        motionLayout = li.inflate(R.layout.custom_view_sticky_slide, this, false) as MotionLayout
        this.addView(motionLayout)

        baseLayout = motionLayout.findViewById(R.id.layout_base)
//        this.hide()
    }

    fun show() {
        this.visibility = View.VISIBLE
        motionLayout.transitionToEnd()
    }

    fun close() {
        motionLayout.transitionToStart()
    }

    fun hide() {
        this.visibility = View.GONE
    }

    override fun addView(child: View?) {
        if (motionLayout == child) {
            super.addView(child)
        } else {
            baseLayout.addView(child)
        }
    }

    override fun addView(child: View?, index: Int) {
        if (motionLayout == child) {
            super.addView(child, index)
        } else {
            baseLayout.addView(child, index)
        }
    }

    override fun addView(child: View?, width: Int, height: Int) {
        if (motionLayout == child) {
            super.addView(child, width, height)
        } else {
            baseLayout.addView(child, width, height)
        }
    }

    override fun addView(child: View?, params: ViewGroup.LayoutParams?) {
        if (motionLayout == child) {
            super.addView(child, params)
        } else {
            baseLayout.addView(child, params)
        }
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        if (motionLayout == child) {
            super.addView(child, index, params)
        } else {
            baseLayout.addView(child, index, params)
        }
    }
}