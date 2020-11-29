package me.sangoh.clone.toss.android.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import me.sangoh.clone.toss.android.utils.CustomResources
import me.sangoh.clone.toss.android.utils.listener.ITextChangedListener

abstract class TossEditText(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    protected val resource: CustomResources = CustomResources(resources)

    abstract fun validation(): Boolean
    abstract fun setOnTextChangedListener(textChangedListener: ITextChangedListener<TossEditText>)

}