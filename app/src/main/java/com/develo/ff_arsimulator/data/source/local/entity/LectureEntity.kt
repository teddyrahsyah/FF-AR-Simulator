package com.develo.ff_arsimulator.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lectures")
data class LectureEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "moduleNumber")
    @NonNull
    val moduleNumber: Int,

    @ColumnInfo(name = "category")
    @NonNull
    val category: String,

    @ColumnInfo(name = "title")
    @NonNull
    val title: String,

    @ColumnInfo(name = "description")
    @NonNull
    val description: String,

    @ColumnInfo(name = "image")
    val image: String? = null,

    @ColumnInfo(name = "modelUrl")
    val modelUrl: String? = null,

//    @ColumnInfo(name = "tags")
//    val tags: List<String>? = null,
)
