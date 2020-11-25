package me.sangoh.clone.toss.android.view.activity.login

import com.example.toss.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import me.sangoh.clone.toss.android.widget.layout.StickySlideLayout

class LoginActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)
    }

    override fun onClick(v: View?) {
    }
}