package me.sangoh.clone.toss.android.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import me.sangeoh.clone.toss.android.R
import me.sangoh.clone.toss.android.widget.stickyslide.BaseStickySlide

class TestActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var slideLayout: BaseStickySlide

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val baseLayout: ViewGroup = findViewById(R.id.baseLayout)

        val btnShow: Button = findViewById(R.id.btn_show)
        btnShow.setOnClickListener(this)

        val btnHide: Button = findViewById(R.id.btn_hide)
        btnHide.setOnClickListener(this)

//        slideLayout = findViewById(R.id.motin)

        val slideLayoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        slideLayout = BaseStickySlide(this)
        baseLayout.addView(slideLayout, slideLayoutParams)

        val textViewLayoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val textview = TextView(this)

        textview.text =
            "아아아앙아아앙아아아앙아아앙아아아앙아아앙아아아앙아아앙아아아앙아아앙아아아앙아아앙아아아앙아아앙아아아앙아아앙아아아앙아아앙아아아앙아아앙아아아앙아아앙아아아앙아"
        slideLayout.addView(textview, textViewLayoutParams)
//        baseLayout.addView(textview, slideLayoutParams)
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.btn_show -> {
                slideLayout.show()
            }

            R.id.btn_hide -> {
                slideLayout.close()
                slideLayout.hide()
            }
        }
    }
}