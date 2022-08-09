package com.develo.ff_arsimulator.ui.contact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.develo.ff_arsimulator.R
import com.develo.ff_arsimulator.base.MainActivity
import com.develo.ff_arsimulator.data.source.Result
import com.develo.ff_arsimulator.databinding.ActivityContactBinding
import com.develo.ff_arsimulator.ui.articles.ArticleViewModel
import com.develo.ff_arsimulator.viewmodel.ViewModelFactory

class ContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.appBarMain.toolbar
        setSupportActionBar(toolbar)
        binding.appBarMain.toolbarTitle.text = toolbar.title
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val viewModel: ContactViewModel by viewModels {
            factory
        }

        val sender = binding.etvEmailEdit.text
        val message = binding.etvMessageEdit.text

        binding.btnSubmit.setOnClickListener {
            viewModel.postMessage(sender.toString(), message.toString()).observe(this) { result ->
                if (result != null) {
                    when (result) {
                        is Result.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Result.PostSuccess -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(
                                this,
                                 result.message,
                                Toast.LENGTH_LONG
                            ).show()
                            Intent(this@ContactActivity, MainActivity::class.java).also {
                                startActivity(it)
                                finish()
                            }
                        }
                        is Result.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(
                                this,
                                "Terjadi kesalahan" + result.error,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        else -> {}
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