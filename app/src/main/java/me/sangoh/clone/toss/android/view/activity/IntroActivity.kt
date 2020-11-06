package me.sangoh.clone.toss.android.view.activity

import android.content.Intent
import android.os.Bundle
import com.example.toss.R

class IntroActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val mainStartIntent = Intent(this, MainActivity::class.java)
        startActivity(mainStartIntent)
        finish()
    }
}