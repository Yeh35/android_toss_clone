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
import me.sangeoh.clone.toss.android.R

class CustomAlarmDialog : Dialog, View.OnClickListener {

    companion object {
        const val OK = 1
        const val NO = -1
    }

    private val activity: Activity
    private val title: String
    private val description: String
    private val onOkClickLiner: DialogInterface.OnClickListener
    private val onNoClickLiner: DialogInterface.OnClickListener?

    private lateinit var btnYes: TextView
    private lateinit var btnNo: TextView
    private lateinit var tvTitle: TextView
    private lateinit var tvDescription: TextView

    constructor(
        activity: Activity,
        title: String,
        description: String,
        onOkClickLiner: DialogInterface.OnClickListener,
        onNoClickLiner: DialogInterface.OnClickListener?
    ) : super(activity) {
        this.activity = activity
        this.title = title
        this.description = description
        this.onOkClickLiner = onOkClickLiner
        this.onNoClickLiner = onNoClickLiner

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    constructor(
        activity: Activity,
        title: String,
        description: String,
        onOkClickLiner: DialogInterface.OnClickListener
    ) : this(activity, title, description, onOkClickLiner, null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_custom_alarm)

        btnYes = findViewById(R.id.btn_yes)
        btnNo = findViewById(R.id.btn_no)
        tvTitle = findViewById(R.id.tv_title)
        tvDescription = findViewById(R.id.tv_description)


        tvTitle.text = title
        tvDescription.text = description
        btnYes.setOnClickListener(this)
        btnNo.setOnClickListener(this)

        if (onNoClickLiner == null) {
            btnNo.visibility = View.GONE
        }
    }

    override fun onClick(view: View?) {
        this.cancel()

        when (view) {
            btnYes -> onOkClickLiner.onClick(this, OK)
            btnNo -> onNoClickLiner!!.onClick(this, NO)
            else -> error("정의되지 않은 클릭입니다.")
        }
    }

}