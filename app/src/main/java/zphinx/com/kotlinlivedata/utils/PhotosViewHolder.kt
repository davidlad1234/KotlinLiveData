package zphinx.com.kotlinlivedata.utils

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.photo_list_content.view.*
import zphinx.com.kotlinlivedata.activities.ListCallback
import zphinx.com.kotlinlivedata.model.pojos.Photo

class PhotosViewHolder internal constructor(private val parentActivity: ListCallback, private val view: View, private val photos: Array<Photo>) : RecyclerView.ViewHolder(view), LayoutContainer {
    override val containerView: View?
        get() = view
    internal var contentView: LinearLayout? = null
    internal var imageView: ImageView? = null
    internal var titleView: TextView? = null
    internal var authorView: TextView? = null
    internal var urlView: TextView? = null

    init {
        urlView = view.url
        authorView = view.author
        contentView = view.content_area
        imageView = view.id_image
        titleView = view.title

        Log.d(TAG, "Initialised view holder")
    }


    fun onClickViews(clickableView: View) {
        val position = clickableView.tag as Int
        val photo = photos[position]
        parentActivity.showDetails(photo, view);
    }

    companion object {
        val TAG = PhotosViewHolder::class.java.simpleName
    }
}

