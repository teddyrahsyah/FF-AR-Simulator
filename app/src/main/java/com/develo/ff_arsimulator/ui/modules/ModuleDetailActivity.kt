package com.develo.ff_arsimulator.ui.modules

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.develo.ff_arsimulator.data.source.Result
import com.develo.ff_arsimulator.databinding.ActivityModuleDetailBinding
import com.develo.ff_arsimulator.viewmodel.ViewModelFactory

class ModuleDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_CATEGORY = "extra_category"
    }

    private lateinit var binding: ActivityModuleDetailBinding
    lateinit var id: String
    lateinit var category: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModuleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.appBarMain.toolbar
        setSupportActionBar(toolbar)
        binding.appBarMain.toolbarTitle.text = toolbar.title
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val viewModel: ModuleViewModel by viewModels {
            factory
        }

        id = intent.extras?.getString(EXTRA_ID).toString()
        category = intent.extras?.getString(EXTRA_CATEGORY).toString()

        when (category) {
            "Theory" -> {
                binding.btnAr.visibility = View.GONE
                getTheory(id, viewModel)
            }
            "Lab" -> {
                binding.btnAr.visibility = View.VISIBLE
                getLab(id, viewModel)
            }
        }
    }

    private fun getTheory(id: String, viewModel: ModuleViewModel) {
        viewModel.getTheoryDetail(id).observe(this) { result ->
            if (result != null) {
                when (result) {
                    is Result.Success -> {
                        val theory = result.data
                        with(binding) {
                            tvModuleTitle.text = theory.moduleTitle
                            tvTitle.text = theory.title
                            tvCategory.text = theory.category
                            tvDescription.text = theory.description
                            Glide.with(this@ModuleDetailActivity)
                                .load(theory.image)
                                .apply(RequestOptions().override(750, 600))
                                .into(ivImage)
                        }
                    }
                    else -> {
                        Toast.makeText(
                            this,
                            "Error occured while retrieving data!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun getLab(id: String, viewModel: ModuleViewModel) {
        viewModel.getLabDetail(id).observe(this) { result ->
            if (result != null) {
                when (result) {
                    is Result.Success -> {
                        val theory = result.data
                        with(binding) {
                            tvModuleTitle.text = theory.moduleTitle
                            tvTitle.text = theory.title
                            tvCategory.text = theory.category
                            tvDescription.text = theory.description
                            Glide.with(this@ModuleDetailActivity)
                                .load(theory.image)
                                .apply(RequestOptions().override(750, 600))
                                .into(ivImage)
                        }
                    }
                    else -> {
                        Toast.makeText(
                            this,
                            "Error occured while retrieving data!",
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