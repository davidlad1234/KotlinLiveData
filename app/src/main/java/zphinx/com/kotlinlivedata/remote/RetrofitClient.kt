package zphinx.com.kotlinlivedata.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofit: Retrofit? = null
    private val BASE_URL = "http://api.flickr.com/services/feeds/"


    // set your desired log level
    // add your other interceptors …
    // add logging as last interceptor
    //
    val retrofitInstance: Retrofit
        get() {
            if (retrofit == null) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                val httpClient = OkHttpClient.Builder()
                httpClient.addInterceptor(logging)
                val builder = Retrofit.Builder()
                builder.baseUrl(BASE_URL)
                val gson = gsonFactory

                builder.addConverterFactory(GsonConverterFactory.create(gson))
                builder.client(httpClient.build())
                        builder.addCallAdapterFactory(CoroutineCallAdapterFactory())
                //builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                retrofit = builder.build()
            }
            return retrofit!!
        }

    private val gsonFactory: Gson
        get() = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create()
}
