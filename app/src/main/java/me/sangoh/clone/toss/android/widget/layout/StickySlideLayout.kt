package me.sangoh.clone.toss.android.widget.layout

import android.content.Context
import android.content.res.Resources
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
) : MotionLayout(context, attrs, defStyleAttr) {

    private val layoutBase: FrameLayout

    init {
        val li = getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        layoutBase = li.inflate(R.layout.widget_sticky_slide_view, this, false) as FrameLayout
        super.addView(layoutBase)

        this.setBackgroundColor(
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                resources.getColor(R.color.transparent_gray, null)
            } else {
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