package com.example.andorid_toss_clone.widget

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.res.getResourceIdOrThrow
import androidx.core.content.res.getStringOrThrow
import com.example.andorid_toss_clone.R

@SuppressLint("AppCompatCustomView", "UseCompatLoadingForDrawables")
class BottomBar(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs), View.OnClickListener {

    //view
    private val baseView: View
    private val btnHome: BottomBarButton
    private val btnConsumption: BottomBarButton
    private val btnRecommendation: BottomBarButton
    private val btnAll: BottomBarButton

    //property

    //data
//    private var onClickListener: OnClickListener? = null

    init {
        val li = getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        baseView = li.inflate(R.layout.widget_bottom_bar, this, false)
        super.addView(baseView)

        btnHome = baseView.findViewById(R.id.btnHome)
        btnConsumption = baseView.findViewById(R.id.btnConsumption)
        btnRecommendation = baseView.findViewById(R.id.btnRecommendation)
        btnAll = baseView.findViewById(R.id.btnAll)

        btnHome.setOnClickListener(this)
        btnConsumption.setOnClickListener(this)
        btnRecommendation.setOnClickListener(this)
        btnAll.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v) {
            btnHome,
            btnConsumption,
            btnRecommendation,
            btnAll -> {
                activateButton(v as BottomBarButton)
            }
            else -> {
                error("정의 되지 않은...")
            }
        }
    }

    private fun activateButton(barButton: BottomBarButton) {
        btnHome.setActivate(barButton == btnHome)
        btnRecommendation.setActivate(barButton == btnRecommendation)
        btnConsumption.setActivate(barButton == btnConsumption)
        btnAll.setActivate(barButton == btnAll)
    }

}

