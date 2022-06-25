package com.develo.ff_arsimulator.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "modules")
data class ModuleEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "moduleNumber")
    val moduleNumber: Int,

    @ColumnInfo(name = "moduleTitle")
    val moduleTitle: String,

    @ColumnInfo(name = "isExpandable")
    var isExpandable: Boolean = false
) {
    fun setExpendable(state: Boolean) {
        this.isExpandable = state
    }
}