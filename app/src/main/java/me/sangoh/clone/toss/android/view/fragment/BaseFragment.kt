package me.sangoh.clone.toss.android.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return provideYourFragmentView(inflater, container, savedInstanceState)
    }

    abstract fun provideYourFragmentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View;
}

