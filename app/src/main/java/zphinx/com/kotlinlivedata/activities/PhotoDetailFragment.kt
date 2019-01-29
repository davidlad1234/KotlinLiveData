package zphinx.com.kotlinlivedata.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.photo_detail.*
import kotlinx.android.synthetic.main.photo_detail.view.*
import zphinx.com.kotlinlivedata.R
import zphinx.com.kotlinlivedata.model.pojos.Photo
import zphinx.com.kotlinlivedata.utils.FMViewUtils
import java.util.*

class PhotoDetailFragment : Fragment() {

    /**
     * The  artist/album this fragment is presenting.
     */


    private var title: String? = null
    private var author: String? = null

    private var photo: Photo? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments!!.containsKey(ARG_TITLE)) {
            title = arguments!!.getString(ARG_TITLE)
            author = arguments!!.getString(ARG_AUTHOR)
            photo = arguments!!.getParcelable(ARG_PHOTO)
            val activity = this.activity as PhotoActivity?
            activity?.setAppBarTitle(title!!)

        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.photo_detail, container, false)
    }

    override fun onStart() {
        super.onStart()
        writeScreen()
    }


    /**
     * Writes to the screen of the fragment
     */
    private fun writeScreen() {
        val activity = activity as PhotoActivity?
        activity!!.setAppBarTitle("$title - $author")

        album_detail.title.text = photo!!.title
        album_detail.author!!.text = photo!!.author

        album_detail.url!!.text = FMViewUtils.fromHtmlCompact(photo!!.link!!)
        album_detail.description!!.text = FMViewUtils.fromHtmlCompact(photo!!.description!!)
        album_detail.tags!!.text = photo!!.tags
        album_detail.author_id!!.text = photo!!.authorId

        writeImage(album_detail.id_image, photo!!)
        writeDate(photo!!.published, album_detail.publish_date)
        writeDate(photo!!.dateTaken, album_detail.date_taken)

    }

    private fun writeImage(imageView: ImageView, photo: Photo) {
        val media = photo.media
        Log.d(TAG, "The media object is: " + media!!)

        val imageString = media.m
        if (imageString != null) {
            FMViewUtils.loadImage( imageView, imageString)
        }
    }

    private fun writeDate(published: Date?, publishedDateView: TextView?) {
        if (published != null) {
            publishedDateView!!.text = published.toString()
        }
    }

    companion object {
        private val TAG = PhotoDetailFragment::class.java.simpleName
        const val ARG_TITLE = "title"
        const val ARG_AUTHOR = "author"
        const val ARG_PHOTO = "photo"
    }


}
