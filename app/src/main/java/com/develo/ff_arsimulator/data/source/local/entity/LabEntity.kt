package com.develo.ff_arsimulator.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "labs")
data class LabEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "moduleId")
    @NonNull
    val moduleId: String,

    @ColumnInfo(name = "moduleNumber")
    @NonNull
    val moduleNumber: Int,

    @ColumnInfo(name = "moduleTitle")
    @NonNull
    val moduleTitle: String,

    @ColumnInfo(name = "labNumber")
    @NonNull
    val labNumber: Int,

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
)