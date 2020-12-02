package me.sangoh.clone.toss.android.widget.edittext

import android.content.Context
import android.text.Editable
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.res.getStringOrThrow
import me.sangeoh.clone.toss.android.R
import me.sangoh.clone.toss.android.utils.listener.ITextChangedListener

class TossTitleEditText(context: Context, attrs: AttributeSet) : TossEditText(context, attrs),
    View.OnFocusChangeListener, TextView.OnEditorActionListener {

    //properties
    val title: String
    val inputType: Int
    var text: String
        get() = editText.text.toString()
        set(value) {
            editText.setText(value)
        }

    private val tvTitle: TextView
    private val editText: EditText
    private val btnPopup: ImageButton
    private val isPopup: Boolean
    private val lineForEdit: View

    private var textChangedListener: ITextChangedListener<TossEditText>? = null
    private var nextView: View? = null

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
            isPopup = getBoolean(R.styleable.TossTitleEditText_is_popup, false)
            inputType = getInt(R.styleable.TossTitleEditText_android_inputType, 1)
        }

        tvTitle = baseView.findViewById(R.id.tv_title)
        editText = baseView.findViewById(R.id.edit_text)
        btnPopup = baseView.findViewById(R.id.btn_popup)
        lineForEdit = baseView.findViewById(R.id.line_for_edit)

        tvTitle.text = title
        if (isPopup) {
            btnPopup.visibility = View.VISIBLE
            editText.inputType = 0
        } else {
            btnPopup.visibility = View.GONE
            editText.inputType = inputType
        }

        editText.onFocusChangeListener = this
        editText.setOnEditorActionListener(this)
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (hasFocus) {
            this.setColor(R.color.blue)
        } else {
            this.setColor(R.color.gray)
        }
    }

    override fun validation(): Boolean {
        return editText.text.toString().length >= 1
    }

    override fun setOnTextChangedListener(textChangedListener: ITextChangedListener<TossEditText>) {
        this.textChangedListener = textChangedListener
    }

    override fun setNextState(nextView: View) {
        editText.imeOptions = EditorInfo.IME_ACTION_NEXT
        this.nextView = nextView
    }

    override fun setDoneState() {
        editText.imeOptions = EditorInfo.IME_ACTION_DONE
        this.nextView = null
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        when(actionId) {
            EditorInfo.IME_ACTION_DONE -> {}
            EditorInfo.IME_ACTION_NEXT -> {
                nextView?.requestFocus()
                textChangedListener?.onTextChanged(this@TossTitleEditText)
                return true
            }
        }
        return false
    }

    private fun setColor(@ColorRes colorId: Int) {
        val settingColor = resource.getColor(colorId)

        tvTitle.setTextColor(settingColor)
        lineForEdit.setBackgroundColor(settingColor)
    }

}