package zphinx.com.kotlinlivedata.utils

import android.os.Build
import android.text.Html
import android.text.Spanned
import android.widget.ImageView
import com.squareup.picasso.Picasso


object FMViewUtils {
    fun loadImage(imageView: ImageView, url: String?) {

        if (!url?.isEmpty()!!) {
            val picasso = Picasso.with(imageView.context)
            picasso.load(url).centerCrop().fit().into(imageView)
        }
    }

    @Suppress("DEPRECATION")
    fun fromHtmlCompact(source: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(source)
        }
    }

}
