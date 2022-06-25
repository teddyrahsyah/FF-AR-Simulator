package com.develo.ff_arsimulator.data.source.local.model

import com.google.gson.annotations.SerializedName

data class ModuleModel(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("moduleNumber")
    val moduleNumber: Int,

    @field:SerializedName("moduleTitle")
    val moduleTitle: String,

    @field:SerializedName("theory")
    val theory: List<Theory>,

    @field:SerializedName("lab")
    val lab: List<Lab>,
)

data class Theory(
    @field:SerializedName("_id")
    val id: String,

    @field:SerializedName("moduleId")
    val moduleId: String,

    @field:SerializedName("moduleNumber")
    val moduleNumber: Int,

    @field:SerializedName("moduleTitle")
    val moduleTitle: String,

    @field:SerializedName("theoryNumber")
    val theoryNumber: Int,

    @field:SerializedName("category")
    val category: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("image")
    val image: String,
)

data class Lab(
    @field:SerializedName("_id")
    val id: String,

    @field:SerializedName("moduleId")
    val moduleId: String,

    @field:SerializedName("moduleNumber")
    val moduleNumber: Int,

    @field:SerializedName("moduleTitle")
    val moduleTitle: String,

    @field:SerializedName("labNumber")
    val labNumber: Int,

    @field:SerializedName("category")
    val category: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("modelAR")
    val modelAR: String,
)