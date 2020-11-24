package me.sangoh.clone.toss.android.widget.layout

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.motion.widget.MotionLayout
import com.example.toss.R

class StickySlideLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val motionBase: MotionLayout

    init {
        val li = getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        motionBase = li.inflate(R.layout.widget_sticky_slide_view, this, false) as MotionLayout
        super.addView(motionBase)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.View,
            0, 0
        ).apply {}
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

}