package tri.pro.kotlinnews.modules.articleScreen

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import tri.pro.kotlinnews.databinding.FragmentArticleBinding
import tri.pro.kotlinnews.models.ObjectResponse
import tri.pro.kotlinnews.utils.NetworkUtils
import java.lang.reflect.Type


class ArticleFragment : Fragment() {
    private lateinit var binding: FragmentArticleBinding
    private lateinit var viewModel: ArticleViewModel
    private lateinit var articleAdapter: ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleBinding.inflate(inflater)
        prepareRecyclerView()

        (activity as AppCompatActivity).supportActionBar!!.title = "Kotlin News"

        viewModel = ViewModelProvider(requireActivity())[ArticleViewModel::class.java]

        val c = NetworkUtils.isNetworkConnected(requireActivity())

        checkDataSource(c)

        return binding.root
    }

    private fun checkDataSource(c: Boolean) {
            if (!c) {
                Toast.makeText(requireActivity(), "No Internet Connection", LENGTH_LONG).show()
                val offlineData = getDataFromSharedPreferences()
                if (offlineData == null || offlineData.data?.children!!.isEmpty()) {
                    Toast.makeText(requireActivity(), "No old data to show", LENGTH_LONG).show()
                } else {
                    articleAdapter.setArticleList(offlineData)
                }
            } else {
                viewModel.getPopularMovies()
                viewModel.observeMovieLiveData().observe(viewLifecycleOwner) { articleList ->
                    articleAdapter.setArticleList(articleList)
                    setDataFromSharedPreferences(articleList)
                }
            }
    }

    private fun prepareRecyclerView() {
        articleAdapter = ArticleAdapter()
        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = articleAdapter

        }
    }

    private fun setDataFromSharedPreferences(obj: ObjectResponse) {
        val gson = Gson()
        val jsonCurObj = gson.toJson(obj)
        val sharedPref: SharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("all_data", jsonCurObj).apply()
    }

    private fun getDataFromSharedPreferences(): ObjectResponse? {
        val gson = Gson()
        val sharedPref: SharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val jsonPreferences = sharedPref.getString("all_data", "")
        return if(jsonPreferences.equals("")){
            null
        }else {
            val type: Type = object : TypeToken<ObjectResponse>() {}.type
            gson.fromJson(jsonPreferences, type)
        }
    }
}