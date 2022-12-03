package tri.pro.kotlinnews.modules.articleDetailScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import tri.pro.kotlinnews.databinding.FragmentArticleDetailBinding


class ArticleDetailFragment : Fragment() {
    private lateinit var binding : FragmentArticleDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleDetailBinding.inflate(inflater)
        setupUI()
        return binding.root
    }

    private fun setupUI() {
        val bundle = arguments
        if (bundle != null) {
            val args = ArticleDetailFragmentArgs.fromBundle(bundle)
            (activity as AppCompatActivity).supportActionBar!!.title = args.article.title
            if(args.article.thumbnail!!.isNotEmpty()){
                binding.ivArticleImage.visibility = View.VISIBLE
                Glide.with(requireActivity())
                    .load(args.article.thumbnail)
                    .into(binding.ivArticleImage)
            }
            if(args.article.selftext!!.isNotEmpty())
                binding.tvArticle.text = args.article.selftext
        }
    }

}