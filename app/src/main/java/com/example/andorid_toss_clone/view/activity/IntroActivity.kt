package com.example.andorid_toss_clone.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.andorid_toss_clone.R

class IntroActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val mainStartIntent = Intent(this, MainActivity::class.java)
        startActivity(mainStartIntent)
        finish()
    }
}