package me.sangoh.clone.toss.android.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    protected val context = getApplication<Application>().applicationContext
    protected val disposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()

        // clear all the subscription
        disposable.clear()
    }

}