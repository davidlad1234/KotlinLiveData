package zphinx.com.kotlinlivedata.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import zphinx.com.kotlinlivedata.R
import zphinx.com.kotlinlivedata.model.pojos.Photo

abstract class PhotoActivity : AppCompatActivity() {
    protected var currentStatus: Int = 0

    override fun onStart() {
        super.onStart()
        currentStatus = STATUS_STARTED
    }

    override fun onPause() {
        super.onPause()
        currentStatus = STATUS_PAUSED
    }

    override fun onResume() {
        super.onResume()
        currentStatus = STATUS_RESUMED
    }

    override fun onStop() {
        super.onStop()
        currentStatus = STATUS_STOPPED
    }


    abstract fun setAppBarTitle(artist: String)

    fun loadFragment(mTwoPane: Boolean,photo:Photo) {
        if (mTwoPane) {
            createFragment(photo)
        } else {

            createActivity(photo)
        }
    }

    private fun createActivity(photo: Photo) {
        val intent = Intent(this, PhotoDetailActivity::class.java)
        intent.putExtra(PhotoDetailFragment.ARG_PHOTO, photo)

        intent.putExtra(PhotoDetailFragment.ARG_TITLE, photo.title)
        intent.putExtra(PhotoDetailFragment.ARG_AUTHOR, photo.author)
        startActivity(intent)
    }

    private fun createFragment(photo: Photo) {
        val arguments = Bundle()
        arguments.putParcelable(PhotoDetailFragment.ARG_PHOTO, photo)
        arguments.putString(PhotoDetailFragment.ARG_TITLE, photo.title)
        arguments.putString(PhotoDetailFragment.ARG_AUTHOR, photo.author)

        val fragment = PhotoDetailFragment()
        fragment.setArguments(arguments)
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.photo_detail_container, fragment)
                .commit()
    }

    companion object {

        val STATUS_STARTED = 1
        val STATUS_RESUMED = 2
        val STATUS_PAUSED = 3
        val STATUS_STOPPED = 4
    }
}
