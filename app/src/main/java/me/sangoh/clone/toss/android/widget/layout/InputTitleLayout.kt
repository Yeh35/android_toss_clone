package me.sangoh.clone.toss.android.widget.layout

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.content.res.getStringOrThrow
import com.example.toss.R

class InputTitleLayout(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private var title: String
    private var defaultErrorMsg: String

    init {


        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.InputTitleLayout,
            0, 0
        ).apply {
            title = getStringOrThrow(R.styleable.InputTitleLayout_title)
            defaultErrorMsg = getStringOrThrow(R.styleable.InputTitleLayout_defaultErrorMsg)
        }
    }

}