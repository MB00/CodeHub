package mb00.android.codehub.api.builder

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton which returns a Retrofit instance
 */

object RetrofitBuilder {

    private var retrofitInstance: Retrofit? = null

    val instance: Retrofit
        get() {
            if (retrofitInstance == null) {
                retrofitInstance = Retrofit.Builder()
                        .baseUrl("https://api.github.com/")
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofitInstance!!
        }

}
