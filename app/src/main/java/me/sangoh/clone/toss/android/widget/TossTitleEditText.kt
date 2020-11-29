package me.sangoh.clone.toss.android.widget

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.res.getStringOrThrow
import com.example.toss.R
import me.sangoh.clone.toss.android.utils.listener.ITextChangedListener

class TossTitleEditText(context: Context, attrs: AttributeSet) : TossEditText(context, attrs),
    View.OnFocusChangeListener, View.OnKeyListener, View.OnClickListener {

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
    private var textChangedListener: ITextChangedListener<TossEditText>? = null

    var clickListener: OnClickListener? = null

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
            inputType = getInt(R.styleable.TossTitleEditText_android_inputType, 1)
        }

        tvTitle = baseView.findViewById(R.id.tv_title)
        tvErrorMessage = baseView.findViewById(R.id.tv_error_message)
        editText = baseView.findViewById(R.id.edit_text)
        btnPopup = baseView.findViewById(R.id.btn_popup)
        lineForEdit = baseView.findViewById(R.id.line_for_edit)

        tvTitle.text = title
        if (isPopup) {
            btnPopup.visibility = View.VISIBLE
            editText.inputType = 0
            btnPopup.setOnClickListener(this)
            editText.setOnClickListener(this)
        } else {
            btnPopup.visibility = View.GONE
            editText.inputType = inputType
        }

        editText.onFocusChangeListener = this
        editText.setOnKeyListener(this)
    }

    override fun setOnClickListener(listener: OnClickListener?) {
        editText.setOnClickListener(listener)
        btnPopup.setOnClickListener(listener)
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        val colorId = if (hasFocus) R.color.blue else R.color.gray
        this.setColor(colorId)
    }

    override fun validation(): Boolean {
        return if (validator != null) {
            validator!!.validation(editText.text.toString())
        } else {
            true
        }
    }

    override fun setOnTextChangedListener(textChangedListener: ITextChangedListener<TossEditText>) {
        this.textChangedListener = textChangedListener
    }

    private fun setColor(@ColorRes colorId: Int) {
        val settingColor = resource.getColor(colorId)

        tvTitle.setTextColor(settingColor)
        lineForEdit.setBackgroundColor(settingColor)
    }

    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        //Enter key Action
        if ((event!!.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
            //Enter key push process
            if (validator != null) {
                if (validator!!.validation(editText.text.toString())) {
                    tvErrorMessage.text = ""
                    setColor(R.color.blue)
                } else {
                    tvErrorMessage.text = defaultErrorMsg
                    setColor(R.color.rad)
                }
            }

            textChangedListener?.onTextChanged(this@TossTitleEditText)
//            return true
            return false
        }
        return true
    }

    override fun requestFocus(direction: Int, previouslyFocusedRect: Rect?): Boolean {
        return if (isPopup) {
            clickListener?.onClick(this)
            return clickListener != null
        } else {
            super.requestFocus(direction, previouslyFocusedRect)
        }
    }

    override fun onClick(v: View?) {
        clickListener?.onClick(this)
    }

}