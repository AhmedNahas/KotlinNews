package tri.pro.kotlinnews.modules.articleScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tri.pro.kotlinnews.models.ObjectResponse
import tri.pro.kotlinnews.remote.RetrofitInstance
import java.util.ArrayList

class ArticleViewModel : ViewModel() {
    private lateinit var currentList: ArrayList<ObjectResponse>
    private var movieLiveData = MutableLiveData<ObjectResponse>()
    fun getPopularMovies() {
        RetrofitInstance.api.getAllData().enqueue(object  : Callback<ObjectResponse> {
            override fun onResponse(call: Call<ObjectResponse>, response: Response<ObjectResponse>) {
                if (response.body()!=null){
                    movieLiveData.value = response.body()
                } else{
                    return
                }
            }
            override fun onFailure(call: Call<ObjectResponse>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }
        })
    }
    fun observeMovieLiveData() : LiveData<ObjectResponse> {
        return movieLiveData
    }

    fun setCurrentList(children: ArrayList<ObjectResponse>) {
        this.currentList = children
    }

    fun getCurrentListSize() : Int{
        return currentList.size
    }
}