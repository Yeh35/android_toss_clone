package me.sangoh.clone.toss.android.view.activity.welcome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_welcome
        )

        val viewModel: WelcomeViewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)
        binding.viewModel = viewModel
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
}



