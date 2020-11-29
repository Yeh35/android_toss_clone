package me.sangoh.clone.toss.android.utils.listener

import android.text.Editable

interface ITextChangedListener<T> {
    fun onTextChanged(target: T)
}
