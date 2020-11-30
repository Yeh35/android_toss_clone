package me.sangoh.clone.toss.android.widget.stickyslide

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TextListStickySlide constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : StickySlide(context, attrs, defStyleAttr) {

    val recyclerView: RecyclerView

    init {
        val recyclerViewLayoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        recyclerView = object: RecyclerView(context){}
        this.addView(recyclerView, recyclerViewLayoutParams)

    }
}
