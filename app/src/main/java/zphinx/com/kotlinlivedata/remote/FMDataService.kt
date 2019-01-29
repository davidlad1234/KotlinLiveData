package zphinx.com.kotlinlivedata.remote



import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import zphinx.com.kotlinlivedata.model.pojos.Photos

interface FMDataService {
    @GET("photos_public.gne")
    fun getPhotos(@Query("tags") photo: String, @Query("format") format: String, @Query("nojsoncallback") handlerValue: String): Deferred<Photos>

}
