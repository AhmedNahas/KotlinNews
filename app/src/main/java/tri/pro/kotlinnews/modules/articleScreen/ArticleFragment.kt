package tri.pro.kotlinnews.modules.articleScreen

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
import tri.pro.kotlinnews.databinding.FragmentArticleBinding
import tri.pro.kotlinnews.utils.NetworkUtils

class ArticleFragment : Fragment() {
    private lateinit var binding : FragmentArticleBinding
    private lateinit var viewModel: ArticleViewModel
    private lateinit var articleAdapter : ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleBinding.inflate(inflater)
        prepareRecyclerView()
        (activity as AppCompatActivity).supportActionBar!!.title = "Kotlin News"
        viewModel = ViewModelProvider(requireActivity())[ArticleViewModel::class.java]
        viewModel.getPopularMovies()
        val c = NetworkUtils.isNetworkConnected(requireActivity())
        if (!c) Toast.makeText(requireActivity(),"No Internet Connection",LENGTH_LONG).show()
        viewModel.observeMovieLiveData().observe(viewLifecycleOwner) { articleList ->
            articleAdapter.setArticleList(articleList)
        }
        return binding.root
    }

    private fun prepareRecyclerView() {
        articleAdapter = ArticleAdapter()
        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = articleAdapter

        }
    }
}