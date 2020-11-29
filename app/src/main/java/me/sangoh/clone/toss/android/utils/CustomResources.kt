package me.sangoh.clone.toss.android.utils

import android.content.res.Resources
import android.content.res.Resources.NotFoundException
import android.content.res.Resources.Theme
import android.os.Build
import androidx.annotation.ColorRes
import kotlin.jvm.Throws

/**
 *
 */
class CustomResources(
    private val resources: Resources
) {

    @Throws(NotFoundException::class)
    fun getColor(@ColorRes id: Int, theme: Theme? = null): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            resources.getColor(id, theme)
        } else {
            @Suppress("DEPRECATION")
            resources.getColor(id)
        }
    }

}