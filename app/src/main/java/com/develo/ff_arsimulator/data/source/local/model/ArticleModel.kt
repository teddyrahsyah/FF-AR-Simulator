package com.develo.ff_arsimulator.data.source.local.model

import com.google.gson.annotations.SerializedName

data class ArticleModel(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("author")
    val author: String,

    @field:SerializedName("category")
    val category: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("date")
    val date: String,
)
