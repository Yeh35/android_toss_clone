package me.sangoh.clone.toss.android.view.activity.welcome

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

    override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
        Log.d("Welcome", "onTransitionTrigger")
    }

    override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
        Log.d("Welcome", "onTransitionStarted")
    }

    override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
        Log.d("Welcome", "onTransitionChange")
    }

    override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
        Log.d("Welcome", "onTransitionCompleted")
    }

    override fun onClick(view: View?) {
        binding.motionBase.visibility = View.VISIBLE
        binding.motionBase.transitionToEnd()
    }


}



