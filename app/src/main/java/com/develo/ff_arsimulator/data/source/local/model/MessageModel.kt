package com.develo.ff_arsimulator.data.source.local.model

import com.google.gson.annotations.SerializedName

data class MessageModel(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("sender")
    val sender: String,

    @field:SerializedName("message")
    val message: String,
)
