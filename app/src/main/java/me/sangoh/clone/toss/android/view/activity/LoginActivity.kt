package me.sangoh.clone.toss.android.view.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.TelephonyManager
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import me.sangeoh.clone.toss.android.R
import me.sangeoh.clone.toss.android.databinding.ActivityLoginBinding
import me.sangoh.clone.toss.android.utils.listener.ITextChangedListener
import me.sangoh.clone.toss.android.viewmodel.LoginViewModel
import me.sangoh.clone.toss.android.widget.edittext.TossEditText
import me.sangoh.clone.toss.android.widget.edittext.TossTitleEditText
import me.sangoh.clone.toss.android.widget.edittext.TossTitleSocialSecurityNumberEditText
import me.sangoh.clone.toss.android.widget.stickyslide.ITextListStickySlideItemClickListener
import me.sangoh.clone.toss.android.widget.stickyslide.TextListStickySlideView


class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login),
    View.OnClickListener, ITextChangedListener<TossEditText>,
    ITextListStickySlideItemClickListener {

    private lateinit var viewModel: LoginViewModel

    private lateinit var tvDescription: TextView
    private lateinit var editName: TossTitleEditText
    private lateinit var editSocialSecurityNumber: TossTitleSocialSecurityNumberEditText
    private lateinit var editNewsAgency: TossTitleEditText
    private lateinit var editPoneNumber: TossTitleEditText
    private lateinit var btnOk: Button

    private lateinit var newsAgencySlideSlide: TextListStickySlideView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.viewModel = viewModel

        //buding
        tvDescription = binding.tvDescription
        editName = binding.editName
        editSocialSecurityNumber = binding.editSocialSecurityNumber
        editNewsAgency = binding.editNewsAgency
        editPoneNumber = binding.editPoneNumber
        btnOk = binding.btnOk

        newsAgencySlideSlide = TextListStickySlideView(this)
        newsAgencySlideSlide.data = viewModel.newsAgencyList
        newsAgencySlideSlide.onClickListener = this
        newsAgencySlideSlide.title = resources.getText(R.string.news_agency) as String
        addContentView(
            newsAgencySlideSlide, ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )

        editSocialSecurityNumber.visibility = View.GONE
        editNewsAgency.visibility = View.GONE
        editPoneNumber.visibility = View.GONE
        btnOk.isEnabled = false

        btnOk.setOnClickListener(this)
        editNewsAgency.setOnClickListener(this)
        editName.setOnTextChangedListener(this)
        editSocialSecurityNumber.setOnTextChangedListener(this)
        editNewsAgency.setOnTextChangedListener(this)

        editName.setNextState(editSocialSecurityNumber)
        editSocialSecurityNumber.setNextState(editNewsAgency)
        editName.requestFocus()
    }

    override fun onClick(v: View?) {
        when (v) {
            btnOk -> {
                val startIntent = Intent(this, MainActivity::class.java)
                this.startActivity(startIntent)
                this.finish()
            }
            editNewsAgency -> {
                newsAgencySlideSlide.show()
            }
            else -> {
                error("잘못된 호출입니다.")
            }
        }
    }

    override fun onTextChanged(target: TossEditText) {
        setNextStep(target)
    }

    override fun onClick(text: String) {
        editNewsAgency.text = text
        setNextStep(editNewsAgency)
    }

    @SuppressLint("MissingPermission", "HardwareIds")
    private fun setNextStep(thisStepEditText: TossEditText) {
        when (thisStepEditText) {
            editName -> {
                if (editSocialSecurityNumber.visibility == View.GONE && thisStepEditText.validation()) {
                    editSocialSecurityNumber.visibility = View.VISIBLE
                    editName.setDoneState()
                    tvDescription.text =
                        resources.getText(R.string.please_input_social_security_number)
                }
            }

            editSocialSecurityNumber -> {
                if (editNewsAgency.visibility == View.GONE && thisStepEditText.validation()) {
                    tvDescription.text = resources.getText(R.string.please_input_news_agency)

                    editNewsAgency.visibility = View.VISIBLE
                    editSocialSecurityNumber.setDoneState()
                    newsAgencySlideSlide.show()
                }
            }

            editNewsAgency -> {
                if (editPoneNumber.visibility == View.GONE && thisStepEditText.validation()) {
                    tvDescription.text = resources.getText(R.string.please_input_phone_number)

                    editPoneNumber.visibility = View.VISIBLE
                    editNewsAgency.setDoneState()
                    editPoneNumber.requestFocus()

                    val telManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager
                    val phoneNum = if (telManager.line1Number.startsWith("+82")) {
                        telManager.line1Number.replace("+82", "0")
                    } else {
                        telManager.line1Number
                    }

                    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(editNewsAgency.windowToken, 0)

                    editPoneNumber.text = phoneNum
                    setNextStep(editPoneNumber)
                }
            }
            editPoneNumber -> {
                if (editPoneNumber.validation()) {
                    tvDescription.text = resources.getText(R.string.please_push_ok)
                }
            }
            else -> {
                error("잘못된 호출입니다.")
            }
        }

        btnOk.isEnabled = editName.validation()
                && editSocialSecurityNumber.validation()
                && editNewsAgency.validation()
                && editPoneNumber.validation()
    }
}