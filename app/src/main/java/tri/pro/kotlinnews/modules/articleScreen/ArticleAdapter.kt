package tri.pro.kotlinnews.modules.articleScreen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tri.pro.kotlinnews.databinding.NewsItemBinding
import tri.pro.kotlinnews.models.ObjectResponse

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    private var articleList = ArrayList<ObjectResponse>()
    @SuppressLint("NotifyDataSetChanged")
    fun setArticleList(articleList: ObjectResponse) {
        this.articleList = articleList.data!!.children as ArrayList<ObjectResponse>
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            NewsItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(articleList[position].data!!.thumbnail!!.isNotEmpty()){
            holder.binding.ivArticleImage.visibility = View.VISIBLE
            Glide.with(holder.itemView)
                .load(articleList[position].data!!.thumbnail)
                .into(holder.binding.ivArticleImage)
        }
        holder.binding.tvArticleName.text = articleList[position].data!!.title
        holder.binding.root.setOnClickListener {
            val action = ArticleFragmentDirections.actionArticleFragmentToArticleDetailFragment(articleList[position].data!!)
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return articleList.size
    }
}