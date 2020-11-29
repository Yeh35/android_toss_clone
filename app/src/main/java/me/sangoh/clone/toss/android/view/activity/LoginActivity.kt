package me.sangoh.clone.toss.android.view.activity

import com.example.toss.R
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.toss.databinding.ActivityLoginBinding
import me.sangoh.clone.toss.android.utils.listener.ITextChangedListener
import me.sangoh.clone.toss.android.viewmodel.LoginViewModel
import me.sangoh.clone.toss.android.widget.TossEditText
import me.sangoh.clone.toss.android.widget.TossTitleEditText
import me.sangoh.clone.toss.android.widget.TossTitleSocialSecurityNumberEditText


class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login),
    View.OnClickListener, ITextChangedListener<TossEditText> {

    private lateinit var viewModel: LoginViewModel

    private lateinit var editName: TossTitleEditText
    private lateinit var editSocialSecurityNumber: TossTitleSocialSecurityNumberEditText
    private lateinit var editNewsAgency: TossTitleEditText
    private lateinit var editPoneNumber: TossTitleEditText
    private lateinit var btnOk: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.viewModel = viewModel

        editName = binding.editName
        editSocialSecurityNumber = binding.editSocialSecurityNumber
        editNewsAgency = binding.editNewsAgency
        editPoneNumber = binding.editPoneNumber
        btnOk = binding.btnOk

        editSocialSecurityNumber.visibility = View.GONE
        editNewsAgency.visibility = View.GONE
        editPoneNumber.visibility = View.GONE

        btnOk.setOnClickListener(this)

        editName.setOnTextChangedListener(this)
        editSocialSecurityNumber.setOnTextChangedListener(this)
        editNewsAgency.setOnTextChangedListener(this)

        editName.requestFocus()
    }

    override fun onClick(v: View?) {
        when(v) {
            btnOk -> {

            }
            else -> {
                error("잘못된 호출입니다.")
            }
        }
    }

    override fun onTextChanged(target: TossEditText) {
        when(target) {
            editName -> {
                if (editSocialSecurityNumber.visibility == View.GONE && target.validation()) {
                    editSocialSecurityNumber.visibility = View.VISIBLE
                }
                val isFocuse =  editSocialSecurityNumber.requestFocus()
                Log.d("isFocuse", isFocuse.toString())
            }
            editSocialSecurityNumber -> {
                if (editNewsAgency.visibility == View.GONE && target.validation()) {
                    editNewsAgency.visibility = View.VISIBLE
                }
                editNewsAgency.requestFocus()
            }
            editNewsAgency -> {
                if (editPoneNumber.visibility == View.GONE && target.validation()) {
                    editPoneNumber.visibility = View.VISIBLE
                }
                editPoneNumber.requestFocus()
            }
            else -> {
                error("잘못된 호출입니다.")
            }
        }
    }

}