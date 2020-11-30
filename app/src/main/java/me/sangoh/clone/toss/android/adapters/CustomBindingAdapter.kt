package me.sangoh.clone.toss.android.adapters

import android.os.Build
import android.text.Html
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import me.sangeoh.clone.toss.android.R
import java.text.DecimalFormat

@BindingAdapter("bind_dowload_count", "bind_security_accident_count")
fun bindWelcomeSubtitle(view: TextView, downloadCount: Int, securityAccidentCount: Int) {
    val formatter = DecimalFormat("#,##0")

    val colorTagStart = "<font color=\"#3182f7\">"
    val colorTagEnd = "</font>"

    val downloadCountText = colorTagStart + formatter.format(downloadCount) + "만" + colorTagEnd
    val securityAccidentCountText =
        colorTagStart + formatter.format(securityAccidentCount) + "건" + colorTagEnd

    val subTitle =
        view.resources.getString(R.string.welcome_toss_is_safe) + "<br>" + view.resources.getString(
            R.string.welcome_boast,
            downloadCountText,
            securityAccidentCountText
        )

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        view.text = Html.fromHtml(subTitle, HtmlCompat.FROM_HTML_MODE_LEGACY)
    } else {
        @Suppress("DEPRECATION")
        view.text = Html.fromHtml(subTitle)
    }
}
