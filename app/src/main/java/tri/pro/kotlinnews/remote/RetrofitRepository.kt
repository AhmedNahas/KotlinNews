package tri.pro.kotlinnews.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    val api : APIsRequests by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.reddit.com/r/Kotlin/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIsRequests::class.java)
    }
}