package me.sangoh.clone.toss.android.widget

import android.annotation.SuppressLint
import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.res.getStringOrThrow
import com.example.toss.R
import me.sangoh.clone.toss.android.utils.listener.ITextChangedListener
import java.text.ParseException
import java.text.SimpleDateFormat

/**
 * 주민번호
 */
class TossTitleSocialSecurityNumberEditText(context: Context, attrs: AttributeSet) :
    TossEditText(context, attrs), View.OnFocusChangeListener, View.OnKeyListener, TextWatcher {

    //properties
    private val title: String
    private val defaultErrorMsg: String

    private val tvTitle: TextView
    private val tvErrorMessage: TextView
    private val editFirst: EditText
    private val editSecondOne: EditText
    private val lineFirst: View
    private val lineSecondOne: View

    private val validator: IValueValidator

    private var textChangedListener: ITextChangedListener<TossEditText>? = null

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
            defaultErrorMsg =
                getStringOrThrow(R.styleable.TossTitleSocialSecurityNumberEditText_defaultErrorMsg)
        }

        tvTitle = baseView.findViewById(R.id.tv_title)
        tvErrorMessage = baseView.findViewById(R.id.tv_error_message)
        editFirst = baseView.findViewById(R.id.edit_first)
        editSecondOne = baseView.findViewById(R.id.edit_second_one)

        lineFirst = baseView.findViewById(R.id.bottom_line_first)
        lineSecondOne = baseView.findViewById(R.id.bottom_line_second_one)

        tvTitle.text = title

        editFirst.filters = arrayOf(InputFilter.LengthFilter(6))
        editSecondOne.filters = arrayOf(InputFilter.LengthFilter(1))

        editFirst.setOnKeyListener(this)
        editSecondOne.setOnKeyListener(this)
        editFirst.onFocusChangeListener = this
        editSecondOne.onFocusChangeListener = this

        validator = object : IValueValidator {
            /**
             * yyMMdds(9902271) 이런식으로 들어온다고 가정
             */
            @SuppressLint("SimpleDateFormat")
            override fun validation(value: String): Boolean {
                if (value.length != 7) {
                    return false
                }

                try {
                    val first = value.substring(0, 6)
                    val formatter = SimpleDateFormat("yyMMdd")
                    formatter.parse(first)
                } catch (e: ParseException) {
                    return false
                }

                try {
                    Integer.parseInt(value.substring(6, 7))
                } catch (e: NumberFormatException) {
                    return false
                }

                return true
            }
        }
    }

    override fun validation(): Boolean {
        return validator.validation(editFirst.text.toString() + editSecondOne.text.toString())
    }

    override fun setOnTextChangedListener(textChangedListener: ITextChangedListener<TossEditText>) {
        this.textChangedListener = textChangedListener
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        when(v) {
            editFirst,
            editSecondOne -> {
//                setColor(if (hasFocus) R.color.blue else R.color.gray)
                this.updateState()
            }
            else -> {
                error("잘못 호출된 케이스")
            }
        }
    }

    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        //Enter key Action
        if ((event!!.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
            //Enter key push process

            when(v) {
                editFirst -> {
                    if (editFirst.text.length < 6) {
                        editFirst.requestFocus()
                    } else {
                        editSecondOne.requestFocus()
                    }
                }
                editSecondOne -> {
                    updateState()
                    textChangedListener?.onTextChanged(this@TossTitleSocialSecurityNumberEditText)
                }

                else -> {
                    error("잘못된 처리")
                }
            }

            return true
        }
        return false
    }

    private fun updateState() {
        if (validation()) {
            tvErrorMessage.text = ""
            if (isFocusable) {
                setColor(R.color.blue)
            } else {
                setColor(R.color.gray)
            }
        } else {
            tvErrorMessage.text = defaultErrorMsg
            setColor(R.color.rad)
        }
    }

    private fun setColor(@ColorRes id: Int) {
        val color = resource.getColor(id)
        tvTitle.setTextColor(color)
        lineFirst.setBackgroundColor(color)
        lineSecondOne.setBackgroundColor(color)
        tvErrorMessage.setTextColor(color)
    }

    override fun afterTextChanged(s: Editable?) {
        if (s!!.length >= 7) {
            editSecondOne.requestFocus()
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

}