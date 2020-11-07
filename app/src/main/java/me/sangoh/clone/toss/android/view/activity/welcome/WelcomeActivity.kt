package me.sangoh.clone.toss.android.view.activity.welcome

import android.os.Bundle
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

class WelcomeActivity : BaseActivity() {

    private lateinit var binding: ActivityWelcomeBinding
    private lateinit var motion: MotionLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_welcome
        )

        val viewModel: WelcomeViewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)
        binding.viewModel = viewModel

//        motion = binding.includeMotion.findViewById(R.id.motion_base)
//        binding.includeMotion.
//        binding.motionBase.transitionToEnd()
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

    fun onClickStart(view: View) {
        motion.transitionToStart()
    }
}



