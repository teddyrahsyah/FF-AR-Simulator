package com.develo.ff_arsimulator.ui.articles

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.develo.ff_arsimulator.data.source.local.entity.ArticleEntity
import com.develo.ff_arsimulator.databinding.ItemArticleBinding
import com.develo.ff_arsimulator.ui.articles.ArticleDetailActivity.Companion.EXTRA_ID

class ArticleAdapter() :
    ListAdapter<ArticleEntity, ArticleAdapter.ArticleViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<ArticleEntity> =
            object : DiffUtil.ItemCallback<ArticleEntity>() {
                override fun areItemsTheSame(
                    oldUser: ArticleEntity,
                    newUser: ArticleEntity
                ): Boolean {
                    return oldUser.id == newUser.id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldUser: ArticleEntity,
                    newUser: ArticleEntity
                ): Boolean {
                    return oldUser == newUser
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article)
    }

    class ArticleViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ArticleEntity) {
            binding.tvArticleTitle.text = article.title
            binding.tvArticleDescription.text = article.description
            Glide.with(itemView.context)
                .load(article.image)
                .apply(RequestOptions().override(750, 600))
                .into(binding.ivArticleImage)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, ArticleDetailActivity::class.java)
                intent.putExtra(EXTRA_ID, article.id)
                itemView.context.startActivity(intent)
            }
        }
    }
}