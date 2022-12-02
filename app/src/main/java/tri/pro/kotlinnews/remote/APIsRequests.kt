package tri.pro.kotlinnews.remote

import retrofit2.Call
import retrofit2.http.GET
import tri.pro.kotlinnews.models.ObjectResponse

interface APIsRequests {

    @GET(ApiEndPoint.JSON_DATA)
    fun getAllData(): Call<ObjectResponse>
}