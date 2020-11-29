package me.sangoh.clone.toss.android.utils.listener

import android.text.Editable

import android.text.TextWatcher

abstract class TextChangedListener<T>(
    private val target: T
) : TextWatcher, ITextChangedListener<T> {
    override fun beforeTextChanged(
        s: CharSequence,
        start: Int,
        count: Int,
        after: Int
    ) {
    }

    override fun onTextChanged(
        s: CharSequence,
        start: Int,
        before: Int,
        count: Int
    ) {
    }

    override fun afterTextChanged(s: Editable) {
        this. onTextChanged(target)
    }
}