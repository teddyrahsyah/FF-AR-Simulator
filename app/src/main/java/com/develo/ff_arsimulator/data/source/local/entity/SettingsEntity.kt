package com.develo.ff_arsimulator.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SettingsEntity(
    val title: String,
    val subtitle: String,
    val thumbnail: Int
) : Parcelable
