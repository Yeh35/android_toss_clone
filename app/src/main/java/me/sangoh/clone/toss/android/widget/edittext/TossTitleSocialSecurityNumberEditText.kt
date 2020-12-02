package me.sangoh.clone.toss.android.widget.edittext

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.text.InputFilter
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.res.getStringOrThrow
import me.sangeoh.clone.toss.android.BuildConfig
import me.sangeoh.clone.toss.android.R
import me.sangoh.clone.toss.android.utils.listener.ITextChangedListener
import java.text.ParseException
import java.text.SimpleDateFormat

/**
 * 주민번호
 */
class TossTitleSocialSecurityNumberEditText(context: Context, attrs: AttributeSet) :
    TossEditText(context, attrs), View.OnFocusChangeListener, TextView.OnEditorActionListener {

    //properties
    private val title: String

    private val tvTitle: TextView
    private val editFirst: EditText
    private val editSecondOne: EditText
    private val lineFirst: View
    private val lineSecondOne: View

    private var textChangedListener: ITextChangedListener<TossEditText>? = null
    private var nextView: View? = null

    init {
        val li = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val baseView =
            li.inflate(R.layout.widget_toss_title_social_security_number_edit_text, this, false)
        super.addView(baseView)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.TossTitleSocialSecurityNumberEditText,
            0, 0
        ).apply {
            title = getStringOrThrow(R.styleable.TossTitleSocialSecurityNumberEditText_title)
        }

        tvTitle = baseView.findViewById(R.id.tv_title)
        editFirst = baseView.findViewById(R.id.edit_first)
        editSecondOne = baseView.findViewById(R.id.edit_second_one)

        lineFirst = baseView.findViewById(R.id.bottom_line_first)
        lineSecondOne = baseView.findViewById(R.id.bottom_line_second_one)

        tvTitle.text = title

        editFirst.filters = arrayOf(InputFilter.LengthFilter(6))
        editSecondOne.filters = arrayOf(InputFilter.LengthFilter(1))

        editFirst.setOnKeyListener { v, keyCode, event ->
            if (BuildConfig.DEBUG && v != editFirst) {
                error("Assertion failed")
            }

            if (event.action == KeyEvent.ACTION_DOWN || keyCode != KeyEvent.KEYCODE_BACKSLASH) {
                if (editFirst.text.length >= 6) {
                    editSecondOne.requestFocus()
                    return@setOnKeyListener true
                }
            }

            false
        }
        editFirst.setOnEditorActionListener(this)
        editSecondOne.setOnEditorActionListener(this)
        editFirst.onFocusChangeListener = this
        editSecondOne.onFocusChangeListener = this
        this.onFocusChangeListener = this
    }

    @SuppressLint("SimpleDateFormat")
    override fun validation(): Boolean {

        if (editFirst.text.toString().length != 6 || editSecondOne.text.toString().length != 1) {
            return false
        }

        try {
            val first = editFirst.text.toString()
            val formatter = SimpleDateFormat("yyMMdd")
            formatter.parse(first)
        } catch (e: ParseException) {
            return false
        }

        try {
            Integer.parseInt(editSecondOne.text.toString())
        } catch (e: NumberFormatException) {
            return false
        }

        return true
    }

    override fun setNextState(nextView: View) {
        this.nextView = nextView
        editSecondOne.imeOptions = EditorInfo.IME_ACTION_NEXT
    }

    override fun setDoneState() {
        this.nextView = null
        editSecondOne.imeOptions = EditorInfo.IME_ACTION_DONE
    }

    override fun setOnTextChangedListener(textChangedListener: ITextChangedListener<TossEditText>) {
        this.textChangedListener = textChangedListener
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (editFirst.isFocused || editSecondOne.isFocused) {
            setColor(R.color.blue)
        } else {
            setColor(R.color.gray)
        }
    }

    private fun setColor(@ColorRes id: Int) {
        val color = resource.getColor(id)
        tvTitle.setTextColor(color)
        lineFirst.setBackgroundColor(color)
        lineSecondOne.setBackgroundColor(color)
    }

    override fun requestFocus(direction: Int, previouslyFocusedRect: Rect?): Boolean {
        return editFirst.requestFocus()
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        when(actionId) {
            EditorInfo.IME_ACTION_DONE -> {}
            EditorInfo.IME_ACTION_NEXT -> {
                when (v) {
                    editFirst -> {
                        editSecondOne.requestFocus()
                    }
                    editSecondOne -> {
                        if (!validation()) {
                            setColor(R.color.rad)
                        }

                        nextView?.requestFocus()
                        textChangedListener?.onTextChanged(this@TossTitleSocialSecurityNumberEditText)
                    }
                    else -> {
                        error("잘못된 처리")
                    }
                }
                return true
            }
        }

        return false
    }

}