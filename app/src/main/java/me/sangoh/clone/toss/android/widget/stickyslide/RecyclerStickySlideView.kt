package me.sangoh.clone.toss.android.widget.stickyslide

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.sangeoh.clone.toss.android.R
import me.sangoh.clone.toss.android.utils.dpToPx

/**
 * StickySlideView에 RecyclerView를 적용한 것이다.
 */
class RecyclerStickySlideView constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    verticalSize: Int = 300,
    title: String,
    data: List<String>
) : StickySlideView(context, attrs, defStyleAttr) {

    private val tvTitle: TextView
    private val btnClear: ImageButton
    private val lineTitle: View

    init {
        val li = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val inflateLayout = li.inflate(R.layout.custom_view_recycler_sticky_slide, null) as LinearLayout
        this.addView(inflateLayout)

        tvTitle = inflateLayout.findViewById(R.id.tv_title)
        btnClear = inflateLayout.findViewById(R.id.btn_clear)
        lineTitle = inflateLayout.findViewById(R.id.line_title)

        // 높이 조절때문에 코드로 추가
        val recyclerView = object : RecyclerView(context) {
            override fun onMeasure(widthSpec: Int, heightSpec: Int) {
                // heightSpec 재정의
                val maxHeightSpec = MeasureSpec.makeMeasureSpec(
                    dpToPx(context, verticalSize.toFloat()),
                    MeasureSpec.AT_MOST
                )
                super.onMeasure(widthSpec, maxHeightSpec)
            }
        }
        inflateLayout.addView(recyclerView, ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ))

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = TextListAdapter(data)
    }
}
