package me.sangoh.clone.toss.android.widget.layout

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.constraintlayout.motion.widget.MotionLayout
import com.example.toss.R

class StickySlideLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val motionBase: MotionLayout
    private val layoutBase: FrameLayout

    init {
        val li = getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        motionBase = li.inflate(R.layout.widget_sticky_slide_view, this, false) as MotionLayout
        layoutBase = motionBase.findViewById(R.id.layout_base)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.View,
            0, 0
        ).apply {}

        super.addView(motionBase)
    }

    fun show() {
        this.visibility = View.VISIBLE
        motionBase.transitionToEnd()
    }

    fun close() {
        motionBase.transitionToStart()
    }

    fun hide() {
        this.visibility = View.GONE
    }

    fun setTransitionListener(listener: MotionLayout.TransitionListener ) {
        motionBase.setTransitionListener(listener)
    }

    override fun addView(child: View?) {
        if (motionBase == child) {
            super.addView(child)
        } else {
            layoutBase.addView(child)
        }
    }

    override fun addView(child: View?, index: Int) {
        if (motionBase == child) {
            super.addView(child, index)
        } else {
            layoutBase.addView(child, index)
        }
    }

    override fun addView(child: View?, width: Int, height: Int) {
        if (motionBase == child) {
            super.addView(child, width, height)
        } else {
            layoutBase.addView(child, width, height)
        }
    }

    override fun addView(child: View?, params: ViewGroup.LayoutParams?) {
        if (motionBase == child) {
            super.addView(child, params)
        } else {
            layoutBase.addView(child, params)
        }
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        if (motionBase == child) {
            super.addView(child, index, params)
        } else {
            layoutBase.addView(child, index, params)
        }
    }
}