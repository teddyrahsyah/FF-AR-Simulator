package com.develo.ff_arsimulator.ui.articles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.develo.ff_arsimulator.data.source.DataRepository

class ArticleViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun getArticles() = dataRepository.getArticles()
    fun getArticleDetail(id: String) = dataRepository.getArticleDetail(id)
}
