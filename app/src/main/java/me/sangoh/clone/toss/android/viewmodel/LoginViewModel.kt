package me.sangoh.clone.toss.android.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.sangoh.clone.toss.android.model.type.NewsAgency
import me.sangoh.clone.toss.android.model.type.PhoneNumber

class LoginViewModel(application: Application) : BaseViewModel(application) {

    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name

    private val _socialSecurityNumber = MutableLiveData<Pair<Int, Int>>()
    val socialSecurityNumber: LiveData<Pair<Int, Int>>
        get() = _socialSecurityNumber

    private val _newsAgency = MutableLiveData<Int>()
    val newsAgency: LiveData<Int>
        get() = _newsAgency

    private val _phoneNumber = MutableLiveData<PhoneNumber>()
    val phoneNumber: LiveData<PhoneNumber>
        get() = _phoneNumber

    private val newsAgencyList: Array<NewsAgency> = NewsAgency.values()

}