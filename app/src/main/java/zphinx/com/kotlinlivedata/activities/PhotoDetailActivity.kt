package zphinx.com.kotlinlivedata.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_photo_detail.*
import zphinx.com.kotlinlivedata.R
import zphinx.com.kotlinlivedata.R.id.detail_toolbar
import zphinx.com.kotlinlivedata.R.id.fab

/**
 * An activity representing a single Photo detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [PhotoListActivity].
 */
class PhotoDetailActivity : PhotoActivity() {
    override fun setAppBarTitle(artist: String) {
        detail_toolbar?.title = artist
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_detail)
        setSupportActionBar(detail_toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, intent.getStringExtra(PhotoDetailFragment.ARG_TITLE), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val fragment = PhotoDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(PhotoDetailFragment.ARG_TITLE,
                            intent.getStringExtra(PhotoDetailFragment.ARG_TITLE))
                    putString(PhotoDetailFragment.ARG_AUTHOR,
                            intent.getStringExtra(PhotoDetailFragment.ARG_AUTHOR))
                    putParcelable(PhotoDetailFragment.ARG_PHOTO,
                            intent.getParcelableExtra(PhotoDetailFragment.ARG_PHOTO))

                }
            }

            supportFragmentManager.beginTransaction()
                    .add(R.id.photo_detail_container, fragment)
                    .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {
                    // This ID represents the Home or Up button. In the case of this
                    // activity, the Up button is shown. For
                    // more details, see the Navigation pattern on Android Design:
                    //
                    // http://developer.android.com/design/patterns/navigation.html#up-vs-back

                    navigateUpTo(Intent(this, PhotoListActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}
