package com.develo.ff_arsimulator.data.source.local.model

import com.google.gson.annotations.SerializedName

data class MessageRequest(
    @SerializedName("sender")
    val sender: String,
    @SerializedName("message")
    val message: String,
)
