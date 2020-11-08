package me.sangoh.clone.toss.android.view.activity.welcome

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.toss.R
import com.example.toss.databinding.ActivityWelcomeBinding
import kotlinx.coroutines.launch
import me.sangoh.clone.toss.android.utils.animationAppearWhileComingUp
import me.sangoh.clone.toss.android.utils.animationRotateSidewaysAndHighlight
import me.sangoh.clone.toss.android.view.activity.BaseActivity
import me.sangoh.clone.toss.android.view.dialog.CustomAlarmDialog

class WelcomeActivity : BaseActivity(), MotionLayout.TransitionListener, View.OnClickListener {

    private lateinit var binding: ActivityWelcomeBinding

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

        binding.btnStart.setOnClickListener(this)
        binding.btnContinue.setOnClickListener(this)
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

                val alarm = CustomAlarmDialog(this, DialogInterface.OnClickListener { dialogInterface, _ ->
                    dialogInterface.cancel()
                })
                alarm.show()
            }
            R.id.end -> { }
            else -> error("정의되지 않은 상태")
        }
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnStart -> {
                binding.motionBase.visibility = View.VISIBLE
                binding.motionBase.transitionToEnd()
            }
            binding.btnContinue -> {
                binding.motionBase.visibility = View.VISIBLE
                binding.motionBase.transitionToStart()
            }
        }
    }

}



