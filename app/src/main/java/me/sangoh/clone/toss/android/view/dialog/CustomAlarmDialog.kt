package me.sangoh.clone.toss.android.view.dialog

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.TextView
import com.example.toss.R

class CustomAlarmDialog : Dialog, View.OnClickListener {

    companion object {
        const val OK = 1
        const val NO = -1
    }

    private val activity: Activity
    private val onOkClickLiner: DialogInterface.OnClickListener
    private val onNoClickLiner: DialogInterface.OnClickListener?

    private lateinit var btnYes: TextView
    private lateinit var btnNo: TextView

    constructor(
        activity: Activity,
        onOkClickLiner: DialogInterface.OnClickListener,
        onNoClickLiner: DialogInterface.OnClickListener?
    ) : super(activity) {
        this.activity = activity;
        this.onOkClickLiner = onOkClickLiner
        this.onNoClickLiner = onNoClickLiner

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    constructor(
        activity: Activity,
        onOkClickLiner: DialogInterface.OnClickListener
    ) : this(activity, onOkClickLiner, null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_custom_alarm)

        btnYes = findViewById(R.id.btn_yes)
        btnYes.setOnClickListener(this)

        btnNo = findViewById(R.id.btn_no)
        btnNo.setOnClickListener(this)

        if (onNoClickLiner == null) {
            btnNo.visibility = View.GONE
        }
    }

    override fun onClick(view: View?) {
        when (view) {
            btnYes -> onOkClickLiner.onClick(this, OK)
            btnNo -> onNoClickLiner!!.onClick(this, NO)
            else -> error("정의되지 않은 클릭입니다.")
        }
    }

}