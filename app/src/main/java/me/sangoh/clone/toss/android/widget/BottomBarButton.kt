package me.sangoh.clone.toss.android.widget

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.getResourceIdOrThrow
import androidx.core.content.res.getStringOrThrow
import me.sangeoh.clone.toss.android.R

@SuppressLint("AppCompatCustomView", "UseCompatLoadingForDrawables")
class BottomBarButton(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    //view
    private val baseView: View
    private val imageView: ImageView
    private val textView: TextView

    //property
    private var text: String
    private var srcActivate: Int
    private var src: Int

    //data
    private var activate: Boolean = false
    private val defaultColor: Int
    private val activateColor: Int

    init {
        val li = getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        baseView = li.inflate(R.layout.widget_bottom_bar_button, this, false)
        super.addView(baseView)

        imageView = baseView.findViewById(R.id.imageView)
        textView = baseView.findViewById(R.id.textView)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.BottomBarButton,
            0, 0
        ).apply {
            src = getResourceIdOrThrow(R.styleable.BottomBarButton_src)
            srcActivate = getResourceIdOrThrow(R.styleable.BottomBarButton_src_activate)
            text = getStringOrThrow(R.styleable.BottomBarButton_text)
        }

        imageView.setImageDrawable(resources.getDrawable(src, null))
        textView.text = text

        defaultColor = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            resources.getColor(R.color.bottom_bar_button, null)
        } else {
            @Suppress("DEPRECATION")
            resources.getColor(R.color.bottom_bar_button)
        }

        activateColor = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            resources.getColor(R.color.bottom_bar_button_activate, null)
        } else {
            @Suppress("DEPRECATION")
            resources.getColor(R.color.bottom_bar_button_activate)
        }

        setOnClickListener {
            super.callOnClick()
            setActivate(true)
        }

    }
    fun setActivate(activate: Boolean) {
        if (this.activate == activate) {
            return
        }

        if (activate) {
            imageView.setImageResource(srcActivate)
            textView.setTextColor(activateColor)
        } else {
            imageView.setImageResource(src)
            textView.setTextColor(defaultColor)
        }

        // 버전이 바뀌면서 위로 이동하는게 사라짐..ㅠㅜ
//        val moveValue = 8f * if (activate) -1 else 1
//        ObjectAnimator.ofFloat(imageView, "translationY", moveValue).apply {
//            duration = 200
//            start()
//        }

        this.activate = activate
    }

    fun getActiviate(): Boolean {
        return activate
    }

 }

