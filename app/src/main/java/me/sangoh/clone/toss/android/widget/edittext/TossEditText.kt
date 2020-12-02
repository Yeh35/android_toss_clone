package me.sangoh.clone.toss.android.widget.edittext

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import me.sangoh.clone.toss.android.utils.CustomResources
import me.sangoh.clone.toss.android.utils.listener.ITextChangedListener

abstract class TossEditText(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    protected val resource: CustomResources = CustomResources(resources)

    /**
     * 값이 변경되면 call back lIstener 호출
     */
    abstract fun setOnTextChangedListener(textChangedListener: ITextChangedListener<TossEditText>)

    /**
     * 값이 올바른지 확인
     */
    abstract fun validation(): Boolean

    /**
     * 가상 키보드를 다음 상태로 만들어놓는다.
     */
    abstract fun setNextState(nextView: View)

    /**
     * 가상 키보드를 완료 상태로 만들어 놓는다.
     */
    abstract fun setDoneState()

}