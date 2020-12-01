package me.sangoh.clone.toss.android.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import me.sangeoh.clone.toss.android.R
import me.sangoh.clone.toss.android.widget.stickyslide.RecyclerStickySlideView
import me.sangoh.clone.toss.android.widget.stickyslide.TextListStickySlideView

class TestActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var slideLayout: TextListStickySlideView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val baseLayout: ViewGroup = findViewById(R.id.baseLayout)
        val btnShow: Button = findViewById(R.id.btn_show)
        val btnHide: Button = findViewById(R.id.btn_hide)
        val btnDataSet: Button = findViewById(R.id.btn_data_set)
        btnShow.setOnClickListener(this)
        btnHide.setOnClickListener(this)
        btnDataSet.setOnClickListener(this)

        val slideLayoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        val data: ArrayList<String> = arrayListOf("가", "나", "다", "라", "마", "바", "사", "아", "자", "차", "카")

        slideLayout = TextListStickySlideView(this)
        slideLayout.data = data
        slideLayout.title = "통신사 선택"
        baseLayout.addView(slideLayout, slideLayoutParams)
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.btn_show -> {
                slideLayout.show()
            }

            R.id.btn_data_set -> {
                slideLayout.data = arrayListOf<String>("SKT", "KT", "LG U+")
            }

            R.id.btn_hide -> {
                slideLayout.close()
            }
        }
    }
}