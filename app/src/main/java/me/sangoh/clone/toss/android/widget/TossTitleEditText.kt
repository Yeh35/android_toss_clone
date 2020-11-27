package me.sangoh.clone.toss.android.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.res.getStringOrThrow
import com.example.toss.R

class TossTitleEditText(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    //properties
    private val title: String
    private val defaultErrorMsg: String

    private val tvTitle: TextView
    private val tvErrorMessage: TextView
    private val editText: EditText
    private val btnPopup: ImageButton
    private val isPopup: Boolean

    init {
        val li = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val baseView = li.inflate(R.layout.widget_toss_title_edit_text, this, false)
        super.addView(baseView)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.TossTitleEditText,
            0, 0
        ).apply {
            title = getStringOrThrow(R.styleable.TossTitleEditText_title)
            defaultErrorMsg = getStringOrThrow(R.styleable.TossTitleEditText_defaultErrorMsg)
            isPopup = getBoolean(R.styleable.TossTitleEditText_is_popup, false)
        }

        tvTitle = baseView.findViewById(R.id.tv_title)
        tvErrorMessage = baseView.findViewById(R.id.tv_error_message)
        editText = baseView.findViewById(R.id.edit_text)
        btnPopup = baseView.findViewById(R.id.btn_popup)

        tvTitle.text = title
        btnPopup.visibility = if (isPopup) View.VISIBLE else View.GONE
    }

    override fun setOnClickListener(l: OnClickListener?) {
        editText.setOnClickListener(l)
        btnPopup.setOnClickListener(l)
    }

}