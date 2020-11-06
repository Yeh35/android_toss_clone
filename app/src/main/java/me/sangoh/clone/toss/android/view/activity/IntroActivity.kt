package me.sangoh.clone.toss.android.view.activity

import android.content.Intent
import android.os.Bundle
import com.example.toss.R
import me.sangoh.clone.toss.android.view.activity.welcome.WelcomeActivity

class IntroActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val startIntent = Intent(this, WelcomeActivity::class.java)
        startActivity(startIntent)
        finish()
    }
}