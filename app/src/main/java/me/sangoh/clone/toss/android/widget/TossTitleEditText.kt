package me.sangoh.clone.toss.android.widget

import android.content.Context
import android.os.Build
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.res.getStringOrThrow
import com.example.toss.R
import me.sangoh.clone.toss.android.utils.listener.TextChangedListener

class TossTitleEditText(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs),
    View.OnFocusChangeListener {

    //properties
    val title: String
    val inputType: Int
    val defaultErrorMsg: String

    private val tvTitle: TextView
    private val tvErrorMessage: TextView
    private val editText: EditText
    private val btnPopup: ImageButton
    private val isPopup: Boolean
    private val lineForEdit: View

    private var validator: IValueValidator? = null

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
            inputType = getInt(R.styleable.TossTitleEditText_android_inputType, 0)
        }

        tvTitle = baseView.findViewById(R.id.tv_title)
        tvErrorMessage = baseView.findViewById(R.id.tv_error_message)
        editText = baseView.findViewById(R.id.edit_text)
        btnPopup = baseView.findViewById(R.id.btn_popup)
        lineForEdit = baseView.findViewById(R.id.line_for_edit)

        tvTitle.text = title
        btnPopup.visibility = if (isPopup) View.VISIBLE else View.GONE
        editText.inputType = inputType

        editText.filters = arrayOf(InputFilter.LengthFilter(3))

        editText.onFocusChangeListener = this
        editText.addTextChangedListener(object : TextChangedListener<EditText>(editText) {
            override fun onTextChanged(target: EditText, s: Editable?) {
                if (validator != null) {
                    if (validator!!.validation(target.text.toString())) {
                        tvErrorMessage.text = ""
                        setColor(R.color.blue)
                    } else {
                        tvErrorMessage.text = defaultErrorMsg
                        setColor(R.color.rad)
                    }
                }
            }
        })
    }

    override fun setOnClickListener(listener: OnClickListener?) {
        editText.setOnClickListener(listener)
        btnPopup.setOnClickListener(listener)
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        val colorId = if (hasFocus) R.color.blue else R.color.gray
        this.setColor(colorId)
    }

    fun validation(): Boolean {
        return if (validator != null) {
            validator!!.validation(editText.text.toString())
        } else {
            true
        }
    }

    private fun setColor(colorId: Int) {
        val settingColor = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            resources.getColor(colorId, null)
        } else {
            @Suppress("DEPRECATION")
            resources.getColor(colorId)
        }

        tvTitle.setTextColor(settingColor)
        lineForEdit.setBackgroundColor(settingColor)
    }
}