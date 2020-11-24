package me.sangoh.clone.toss.android.view.activity.welcome

import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.toss.R
import com.example.toss.databinding.ActivityWelcomeBinding
import kotlinx.coroutines.launch
import me.sangoh.clone.toss.android.view.activity.BaseActivity
import me.sangoh.clone.toss.android.view.dialog.CustomAlarmDialog
import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.LayoutInflater
import android.widget.Button
import android.widget.FrameLayout
import me.sangoh.clone.toss.android.utils.*
import me.sangoh.clone.toss.android.view.activity.login.LoginActivity

class WelcomeActivity : BaseActivity(), MotionLayout.TransitionListener, View.OnClickListener {

    private val RESULT_CODE_SETTING = 20105

    private lateinit var binding: ActivityWelcomeBinding
    private var permissionDeniedCount = 0

    private lateinit var btnContinue: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_welcome
        )

        val viewModel: WelcomeViewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)
        binding.viewModel = viewModel

        binding.motionBase.visibility = View.GONE
        binding.motionBase.setTransitionListener(this)

        val li = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layoutPermission = li.inflate(R.layout.layout_permission, binding.motionBase, false)
        binding.motionBase.addView(layoutPermission)

        btnContinue = layoutPermission.findViewById(R.id.btn_continue)

        binding.btnStart.setOnClickListener(this)
        btnContinue.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()

        Thread(Runnable {
            scopeMain.launch {
                animationAppearWhileComingUp(binding.layoutForAnimation)
            }

            Thread.sleep(1500)

            scopeMain.launch {
                animationRotateSidewaysAndHighlight(binding.ivGuard)
            }
        }).start()
    }

    override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {}
    override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {}
    override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {}

    override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
        Log.d("Welcome", "onTransitionCompleted currentId(${currentId})")
        when (currentId) {
            R.id.start -> {
                if (requestPermissions()) {
                    nextStep()
                }
            }
            R.id.end -> {
            }
            else -> error("정의되지 않은 상태")
        }
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnStart -> {
//                binding.motionBase.visibility = View.VISIBLE
//                binding.motionBase.transitionToEnd()
                binding.motionBase.show()
            }
            btnContinue -> {
//                binding.motionBase.visibility = View.VISIBLE
//                binding.motionBase.transitionToStart()
                binding.motionBase.close()
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
                description,
                DialogInterface.OnClickListener { _, _ ->

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
                })
            alarm.show()
            return
        }

        //전부 pass 한경우
        nextStep()

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
}



