package me.sangoh.clone.toss.android.view.activity

import com.example.toss.R
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import com.example.toss.databinding.ActivityLoginBinding


class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login), View.OnClickListener {

    private lateinit var editName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)

    }

    override fun onClick(v: View?) {

    }
}