package com.develo.ff_arsimulator.ui.articles

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.develo.ff_arsimulator.R
import com.develo.ff_arsimulator.data.source.Result
import com.develo.ff_arsimulator.databinding.ActivityArticleDetailBinding
import com.develo.ff_arsimulator.viewmodel.ViewModelFactory

class ArticleDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityArticleDetailBinding
    lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.appBarMain.toolbar
        setSupportActionBar(toolbar)
        binding.appBarMain.toolbarTitle.text = toolbar.title
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val viewModel: ArticleViewModel by viewModels {
            factory
        }

        id = intent.extras?.getString(EXTRA_ID).toString()

        getArticleData(id, viewModel)

    }

    private fun getArticleData(id: String, viewModel: ArticleViewModel) {
        viewModel.getArticleDetail(id).observe(this) { result ->
            if (result != null) {
                when (result) {
                    is Result.Success -> {
                        val article = result.data
                        with(binding) {
                            tvTitle.text = article.title
                            tvCreated.text = getString(R.string.article_created_text, article.author, article.date?.take(10))
                            tvCategory.text = getString(R.string.article_category_text, article.category)
                            tvDescription.text = HtmlCompat.fromHtml(article.description, HtmlCompat.FROM_HTML_MODE_COMPACT)
                            Glide.with(this@ArticleDetailActivity)
                                .load(article.image)
                                .apply(RequestOptions().override(750, 600))
                                .into(ivImage)
                        }
                    }
                    else -> {
                        Toast.makeText(
                            this,
                            "Error Occured while retrieving data!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}