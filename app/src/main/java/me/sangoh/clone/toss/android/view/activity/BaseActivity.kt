package me.sangoh.clone.toss.android.view.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

/**
 * T는 DataBuding할 Class를 넣어준다.
 */
abstract class BaseActivity<T : ViewDataBinding>(
    private val activityId: Int
) : AppCompatActivity() {

    protected val scopeIo = CoroutineScope(Dispatchers.IO)
    protected val scopeMain = CoroutineScope(Dispatchers.Main)

    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityId)

        binding = DataBindingUtil.setContentView(
            this,
            activityId
        )
    }


}