package me.sangoh.clone.toss.android.widget.stickyslide

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.recyclerview.widget.RecyclerView
import me.sangeoh.clone.toss.android.R
import me.sangoh.clone.toss.android.utils.dpToPx

/**
 * StickySlideView에 RecyclerView를 적용한 것이다.
 */
abstract class RecyclerStickySlideView constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : StickySlideView(context, attrs, defStyleAttr) {

    //properties
    var title: String
        get() {
            return tvTitle.text.toString()
        }
        set(value) {
            tvTitle.text = value
        }

    protected var verticalSize: Int = 300
    protected val recyclerView: RecyclerView

    private val tvTitle: TextView
    private val btnClear: ImageButton
    private val lineTitle: View

    private var subTrasitionListener: ITransitionListener? = null

    init {
//        val li = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val inflateLayout =
            View.inflate(context, R.layout.custom_view_recycler_sticky_slide, null) as LinearLayout
        this.addView(inflateLayout)

        tvTitle = inflateLayout.findViewById(R.id.tv_title)
        btnClear = inflateLayout.findViewById(R.id.btn_clear)
        lineTitle = inflateLayout.findViewById(R.id.line_title)

        // 높이 조절때문에 코드로 추가
        recyclerView = object : RecyclerView(context) {
            override fun onMeasure(widthSpec: Int, heightSpec: Int) {
                // heightSpec 재정의
                val maxHeightSpec = MeasureSpec.makeMeasureSpec(
                    dpToPx(context, verticalSize.toFloat()),
                    MeasureSpec.AT_MOST
                )
                super.onMeasure(widthSpec, maxHeightSpec)
            }
        }
        recyclerView.isFocusable = true
        recyclerView.isFocusableInTouchMode = true

        inflateLayout.addView(
            recyclerView, ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )

        btnClear.setOnClickListener {
            this.close()
        }

        super.setTransitionListener(object : ITransitionListener {
            override fun onTransitionCompleted(layout: MotionLayout, state: StickySlideState) {
                if (state == StickySlideState.SHOW) {
                    this@RecyclerStickySlideView.transitionEnable(false)
                }

                subTrasitionListener?.onTransitionCompleted(layout, state)
            }
        })
    }

    /**
     * 해당 클래스에서 사용해야해서 랩핑하였다.
     */
    override fun setTransitionListener(i: ITransitionListener) {
        subTrasitionListener = i
    }
}
