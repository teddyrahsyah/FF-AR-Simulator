package com.develo.ff_arsimulator.data.source.remote.response

import com.develo.ff_arsimulator.data.source.local.model.ArticleModel
import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    @field:SerializedName("results")
    val results: List<ArticleModel>
)
