package zphinx.com.kotlinlivedata.activities

import android.content.Context
import android.view.View
import zphinx.com.kotlinlivedata.model.pojos.Photo

interface ListCallback {

    fun getActivityContext(): Context

    fun showDetails(photo: Photo, view: View)
}