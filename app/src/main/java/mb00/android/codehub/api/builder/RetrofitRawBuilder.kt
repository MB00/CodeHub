package mb00.android.codehub.api.builder

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitRawBuilder {

    private var retrofitInstance: Retrofit? = null

    val instance: Retrofit
        get() {
            if (retrofitInstance == null) {
                retrofitInstance = Retrofit.Builder()
                        .baseUrl("https://raw.githubusercontent.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofitInstance!!
        }

}
