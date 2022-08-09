package com.develo.ff_arsimulator.data.source.remote.response

import com.develo.ff_arsimulator.data.source.local.model.ArticleModel
import com.develo.ff_arsimulator.data.source.local.model.MessageModel
import com.google.gson.annotations.SerializedName

data class MessageResponse(
    @field:SerializedName("results")
    val results: List<MessageModel>
)
