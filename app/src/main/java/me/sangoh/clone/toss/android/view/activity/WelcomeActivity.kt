package me.sangoh.clone.toss.android.view.activity

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.launch
import me.sangoh.clone.toss.android.view.dialog.CustomAlarmDialog
import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import me.sangeoh.clone.toss.android.R
import me.sangeoh.clone.toss.android.databinding.ActivityWelcomeBinding
import me.sangoh.clone.toss.android.utils.*
import me.sangoh.clone.toss.android.viewmodel.WelcomeViewModel
import me.sangoh.clone.toss.android.widget.stickyslide.ITransitionListener
import me.sangoh.clone.toss.android.widget.stickyslide.StickySlideState
import me.sangoh.clone.toss.android.widget.stickyslide.StickySlideView

class WelcomeActivity : BaseActivity<ActivityWelcomeBinding>(R.layout.activity_welcome),
    ITransitionListener, View.OnClickListener {
    private val RESULT_CODE_SETTING = 20105

    private var permissionDeniedCount = 0

    private lateinit var btnContinue: Button
    private lateinit var slideLayout: StickySlideView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = ViewModelProvider(this).get(
            WelcomeViewModel::class.java
        )

//        slideLayout = binding.motionBase
        slideLayout = StickySlideView(this)
        this.addContentView(slideLayout, ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT,
        ))

        val li = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layoutPermission = li.inflate(R.layout.layout_permission, slideLayout, false)
        slideLayout.addView(layoutPermission)

        btnContinue = layoutPermission.findViewById(R.id.btn_continue)

        slideLayout.visibility = View.GONE
        slideLayout.setTransitionListener(this)

        binding.btnStart.setOnClickListener(this)
        btnContinue.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()

        Thread {
            scopeMain.launch {
                animationAppearWhileComingUp(binding.layoutForAnimation)
            }

            Thread.sleep(1500)

            scopeMain.launch {
                animationRotateSidewaysAndHighlight(binding.ivGuard)
            }
        }.start()
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnStart -> {
//                binding.motionBase.visibility = View.VISIBLE
//                binding.motionBase.transitionToEnd()
                slideLayout.show()
            }
            btnContinue -> {
//                binding.motionBase.visibility = View.VISIBLE
//                binding.motionBase.transitionToStart()
                slideLayout.close()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // 성공시 0,  -1,

        if (permissions.size < 0) {
            return
        }

        val title: String
        val description: String

        for (i in permissions.indices) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                continue
            }

            permissionDeniedCount++

            when (permissions[i]) {
                Manifest.permission.READ_PHONE_NUMBERS -> {
                    title = resources.getString(R.string.please_allow_access)
                    description =
                        resources.getString(R.string.please_allow_access_making_and_managing_calls)
                }
                Manifest.permission.READ_CONTACTS -> {
                    title = resources.getString(R.string.please_allow_access)
                    description = resources.getString(R.string.please_allow_access_read_contacts)
                }
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE -> {
                    title = resources.getString(R.string.please_allow_access)
                    description = resources.getString(R.string.please_allow_access_storage)
                }
                else -> {
                    error("정의되지 않은 요청입니다.")
                }
            }

            val alarm = CustomAlarmDialog(
                this,
                title,
                description
            ) { _, _ ->

                if (permissionDeniedCount > 2) {
                    val intent = Intent(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.fromParts("package", packageName, null)
                    )
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivityForResult(intent, RESULT_CODE_SETTING)   // 6
                } else {
                    requestPermissions()
                }
            }
            alarm.show()
            return
        }

        //전부 pass 한경우
        if (requestPermissions()) {
            nextStep()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PERMISSION_REQUEST_CODE) {
            requestPermissions()
        }
    }

    private fun requestPermissions(): Boolean {
        var resultBoolean = true
        resultBoolean = resultBoolean && requestReadPhoneNumbersPermission(this)
        resultBoolean = resultBoolean && requestReadContactsPermission(this)
        resultBoolean = resultBoolean && requestStoragePermission(this)
        return resultBoolean
    }

    private fun nextStep() {
        val startIntent = Intent(this, LoginActivity::class.java)
        startActivity(startIntent)
        this.finish()
    }

    override fun onTransitionCompleted(layout: MotionLayout, state: StickySlideState) {
        Log.d("Welcome", "onTransitionCompleted state(${state})")

        @Suppress("REDUNDANT_ELSE_IN_WHEN")
        when (state) {
            StickySlideState.SHOW -> { }
            StickySlideState.CLOSE -> {
                if (requestPermissions()) {
                    nextStep()
                }
            }
            else -> error("정의되지 않은 상태")
        }
    }
}



