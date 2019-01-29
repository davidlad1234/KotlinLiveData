package zphinx.com.kotlinlivedata.utils

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import zphinx.com.kotlinlivedata.R
import zphinx.com.kotlinlivedata.activities.ListCallback
import zphinx.com.kotlinlivedata.model.pojos.Photo


class PhotoRecyclerViewAdapter(private val listCallback: ListCallback,
                               private val photos: Array<Photo>?) : RecyclerView.Adapter<PhotosViewHolder>() {

    init {
        Log.d(TAG, "Initialised recycler view with hotos.size: ")
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): PhotosViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.photo_list_content, viewGroup, false)
        Log.d(TAG, "creating view holder..................")
        return PhotosViewHolder(listCallback, view, photos!!)

    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        val photo = photos!![position]
        Log.d(TAG, "Binding view holder . .....$photo")
            fetchImage(holder.imageView, photo)
            holder.imageView?.tag  = position
            holder.contentView?.tag = position
            holder.titleView?.text = photo.title

            holder.authorView?.text = photo.author

            holder.urlView?.text = FMViewUtils.fromHtmlCompact(photo.link!!)
            holder.onClickViews(holder.imageView!!)
            holder.onClickViews(holder.contentView!!)
            Log.d(TAG, "Bound view holder ...success..")


    }


    private fun fetchImage(imageView: ImageView?, photo: Photo?) {
        if (photo != null) {
            val media = photo.media
            val imageLocation: String? = media?.m
            FMViewUtils.loadImage( imageView!!, imageLocation)
        } else {
            Log.d(TAG, "Photo image is not available...")
        }
    }

    override fun getItemCount(): Int {
        return photos?.size ?: 0
    }

    companion object {

        val TAG: String = PhotoRecyclerViewAdapter::class.java.simpleName
    }

}
