package zphinx.com.kotlinlivedata.activities

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_photo_list.*
import kotlinx.android.synthetic.main.photo_list.*
import zphinx.com.kotlinlivedata.R
import zphinx.com.kotlinlivedata.model.PhotoViewModel
import zphinx.com.kotlinlivedata.model.pojos.Photo
import zphinx.com.kotlinlivedata.utils.PhotoRecyclerViewAdapter

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [PhotoDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class PhotoListActivity : PhotoActivity(), ListCallback {
    override fun getActivityContext(): Context {
        return this@PhotoListActivity.baseContext
    }

    override fun setAppBarTitle(artist: String) {
        toolbar.title = artist
    }

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    private lateinit var viewModel: PhotoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Click on a photo", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        if (photo_detail_container != null) {

            twoPane = true
        }
        viewModel = ViewModelProviders.of(this).get(PhotoViewModel::class.java)
    }


    override fun onStart() {
        super.onStart()
        val photos: MutableLiveData<Array<Photo>>? = viewModel.getPhotos()
        subscribeToModel(photos!!)


    }

    private fun subscribeToModel(photos: MutableLiveData<Array<Photo>>) {
        // Observe product data
        photos.observe(this, Observer<Array<Photo>> { t ->
            Log.d(TAG, "Photos has changed!! ................................$t")
            showPhotos(t!!)
        })
    }

    private fun showPhotos(photos: Array<Photo>) {

        album_list.adapter = PhotoRecyclerViewAdapter(this@PhotoListActivity, photos)


    }

    override fun showDetails(photo: Photo, view: View) {
        val onClickListener = View.OnClickListener { v ->
             if (twoPane) {
                val fragment = PhotoDetailFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(PhotoDetailFragment.ARG_PHOTO, photo)
                        putString(PhotoDetailFragment.ARG_TITLE, photo.title)
                        putString(PhotoDetailFragment.ARG_AUTHOR, photo.author)

                    }
                }
                this@PhotoListActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.photo_detail_container, fragment)
                        .commit()
            } else {
                val intent = Intent(v.context, PhotoDetailActivity::class.java).apply {
                    putExtra(PhotoDetailFragment.ARG_PHOTO, photo)
                    putExtra(PhotoDetailFragment.ARG_TITLE, photo.title)
                    putExtra(PhotoDetailFragment.ARG_AUTHOR, photo.author)
                }
                v.context.startActivity(intent)
            }
        }
        view.setOnClickListener(onClickListener)

    }

    companion object {
        val TAG = PhotoListActivity::class.java.simpleName
    }
}
