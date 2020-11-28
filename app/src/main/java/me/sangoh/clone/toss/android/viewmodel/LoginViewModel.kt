package me.sangoh.clone.toss.android.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.sangoh.clone.toss.android.view.BaseViewModel

class LoginViewModel(application: Application) : BaseViewModel(application) {

    private val _downloadCount = MutableLiveData<Int>(4000)
    val downloadCount: LiveData<Int>
        get() = _downloadCount

    private val _securityAccidentCount = MutableLiveData<Int>(0)
    val securityAccidentCount: LiveData<Int>
        get() = _securityAccidentCount

}