package me.sangoh.clone.toss.android.widget.stickyslide

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import me.sangeoh.clone.toss.android.R

class BaseStickySlide constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MotionLayout(context, attrs, defStyleAttr) {

    private val layoutBase: FrameLayout

    companion object {
        fun setContext(context: Context): BaseStickySlide {
            val slide = BaseStickySlide(context)
            slide.loadLayoutDescription(R.xml.widget_sticky_slide_view_scene)
            return slide
        }
    }

    init {
        val layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.bottomToTop = ConstraintLayout.LayoutParams.PARENT_ID
        layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
        layoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID

        layoutBase = FrameLayout(context)
        layoutBase.background = ContextCompat.getDrawable(context, R.drawable.rounded_white_layout)
        layoutBase.id = R.id.layout_base

        this.addView(layoutBase, layoutParams)

        this.setBackgroundColor(
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                resources.getColor(R.color.transparent_gray, null)
            } else {
                @Suppress("DEPRECATION")
                resources.getColor(R.color.transparent_gray)
            }
        )
        this.loadLayoutDescription(R.xml.widget_sticky_slide_view_scene)
        this.hide()
    }

    fun show() {
        this.visibility = View.VISIBLE
        this.transitionToEnd()
    }

    fun close() {
        this.transitionToStart()
    }

    fun hide() {
        this.visibility = View.GONE
    }

    override fun addView(child: View?) {
        if (layoutBase == child) {
            super.addView(child)
        } else {
            layoutBase.addView(child)
        }
    }

    override fun addView(child: View?, index: Int) {
        if (layoutBase == child) {
            super.addView(child, index)
        } else {
            layoutBase.addView(child, index)
        }
    }

    override fun addView(child: View?, width: Int, height: Int) {
        if (layoutBase == child) {
            super.addView(child, width, height)
        } else {
            layoutBase.addView(child, width, height)
        }
    }

    override fun addView(child: View?, params: ViewGroup.LayoutParams?) {
        if (layoutBase == child) {
            super.addView(child, params)
        } else {
            layoutBase.addView(child, params)
        }
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        if (layoutBase == child) {
            super.addView(child, index, params)
        } else {
            layoutBase.addView(child, index, params)
        }
    }
}