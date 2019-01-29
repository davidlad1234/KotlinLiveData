package zphinx.com.kotlinlivedata.model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import kotlinx.coroutines.experimental.launch
import zphinx.com.kotlinlivedata.model.pojos.Photo
import zphinx.com.kotlinlivedata.remote.FMDataService
import zphinx.com.kotlinlivedata.remote.RetrofitClient

class PhotoViewModel : ViewModel() {
    val PHOTO = "pictures"
    val HANDLER_VALUE = "1"
    val JSON = "json"
    private var photoList: MutableLiveData<Array<Photo>>? = null

    fun getPhotos(): MutableLiveData<Array<Photo>>? {

        if (photoList == null) {
            photoList = MutableLiveData()
            launch {
                fetchPhotos(photoList!!)
            }
        }
        return photoList;

    }

    /**
     * Makes the http request and sends the Results to the adapter.
     */
    suspend fun fetchPhotos(dList: MutableLiveData<Array<Photo>>) {
        val retroClient = RetrofitClient.retrofitInstance
        val dataService = retroClient.create(FMDataService::class.java!!)
        try {
            val request = dataService.getPhotos(PHOTO, JSON, HANDLER_VALUE)
            val response = request.await()

            Log.d(TAG, "Received from server: "+response.photos)

            dList?.postValue(response.photos!!)


        } catch (e: Exception) {
            Log.d(TAG, "Error:!! from server: "+e.message)

        }
    }

    companion object {
        val TAG = PhotoViewModel::class.java.simpleName
    }


    override fun onCleared() {
        super.onCleared()
    }
}
