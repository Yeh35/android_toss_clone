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

/**
 * 주민번호
 */
class TossTitleSocialSecurityNumberEditText(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    //properties
    private val title: String
    private val defaultErrorMsg: String

    private val tvTitle: TextView
    private val tvErrorMessage: TextView
    private val editFirst: EditText
    private val editSecondOne: EditText

    init {
        val li = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val baseView = li.inflate(R.layout.widget_toss_title_social_security_number_edit_text, this, false)
        super.addView(baseView)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.TossTitleSocialSecurityNumberEditText,
            0, 0
        ).apply {
            title = getStringOrThrow(R.styleable.TossTitleSocialSecurityNumberEditText_title)
            defaultErrorMsg = getStringOrThrow(R.styleable.TossTitleSocialSecurityNumberEditText_defaultErrorMsg)
        }

        tvTitle = baseView.findViewById(R.id.tv_title)
        tvErrorMessage = baseView.findViewById(R.id.tv_error_message)
        editFirst = baseView.findViewById(R.id.edit_first)
        editSecondOne = baseView.findViewById(R.id.edit_second_one)

        tvTitle.text = title
    }

}