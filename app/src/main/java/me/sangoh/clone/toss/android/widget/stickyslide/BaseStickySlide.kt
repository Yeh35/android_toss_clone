package me.sangoh.clone.toss.android.widget.stickyslide

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import me.sangeoh.clone.toss.android.R

class BaseStickySlide @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val motionLayout: MotionLayout
    private val layoutBase: FrameLayout

    init {
        val li = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        motionLayout = li.inflate(R.layout.custom_view_sticky_slide, this, false) as MotionLayout
        this.addView(motionLayout)

        layoutBase = motionLayout.findViewById(R.id.layout_base)
        println(layoutBase.id)
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
            layoutBase.addView(child)
        }
    }

    override fun addView(child: View?, index: Int) {
        if (motionLayout == child) {
            super.addView(child, index)
        } else {
            layoutBase.addView(child, index)
        }
    }

    override fun addView(child: View?, width: Int, height: Int) {
        if (motionLayout == child) {
            super.addView(child, width, height)
        } else {
            layoutBase.addView(child, width, height)
        }
    }

    override fun addView(child: View?, params: ViewGroup.LayoutParams?) {
        if (motionLayout == child) {
            super.addView(child, params)
        } else {
            layoutBase.addView(child, params)
        }
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        if (motionLayout == child) {
            super.addView(child, index, params)
        } else {
            layoutBase.addView(child, index, params)
        }
    }
}