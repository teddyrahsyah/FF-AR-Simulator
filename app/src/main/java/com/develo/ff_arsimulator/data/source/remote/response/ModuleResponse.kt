package com.develo.ff_arsimulator.data.source.remote.response

import com.develo.ff_arsimulator.data.source.local.model.ModuleModel
import com.google.gson.annotations.SerializedName

data class ModuleResponse(
    @field:SerializedName("results")
    val results: List<ModuleModel>
)
