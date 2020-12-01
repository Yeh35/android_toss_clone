package me.sangoh.clone.toss.android.widget.stickyslide

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.MotionScene
import me.sangeoh.clone.toss.android.R

open class StickySlideView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    protected val baseLayout: FrameLayout
    private val motionLayout: MotionLayout

    private val transition: MotionScene.Transition
    private var trasitionListener: ITrasitionListener? = null

    init {
        this.visibility = View.GONE

        val li = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        motionLayout = li.inflate(R.layout.custom_view_sticky_slide, null, false) as MotionLayout
        this.addView(motionLayout)

        val closeView: View = motionLayout.findViewById(R.id.view_close)
        closeView.setOnClickListener {
            this.close()
        }

        baseLayout = motionLayout.findViewById(R.id.layout_base)

        motionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(layout: MotionLayout?, startId: Int, endId: Int) {}
            override fun onTransitionChange(layout: MotionLayout?,startId: Int,endId: Int,progress: Float) {}
            override fun onTransitionTrigger(layout: MotionLayout?,triggerId: Int,positive: Boolean,progress: Float) {}
            override fun onTransitionCompleted(layout: MotionLayout?, currentId: Int) {
                if (currentId == R.id.start) {
                    this@StickySlideView.visibility = View.GONE
                    trasitionListener?.onTransitionCompleted(motionLayout, StickySlideState.CLOSE)
                } else {
                    baseLayout.requestFocus()
                    trasitionListener?.onTransitionCompleted(motionLayout, StickySlideState.SHOW)
                }
            }
        })

        transition = motionLayout.getTransition(R.id.transition_sticky_slide)
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

    fun show() {
        this.visibility = View.VISIBLE
        motionLayout.transitionToEnd()
    }

    fun close() {
        motionLayout.transitionToStart()
    }

    open fun setTrasitionListener(listener: ITrasitionListener) {
        this.trasitionListener = listener
    }

    protected fun transitionEnable(boolean: Boolean) {
        if (transition.isEnabled != boolean) {
            transition.setEnable(boolean)
        }
    }

}