package com.develo.ff_arsimulator.di

import android.content.Context
import com.develo.ff_arsimulator.data.source.DataRepository
import com.develo.ff_arsimulator.data.source.local.room.AppRoomDatabase
import com.develo.ff_arsimulator.data.source.remote.api.ApiConfig

object Injection {
    fun provideRepository(context: Context): DataRepository {
        val apiService = ApiConfig.getApiService()
        val database = AppRoomDatabase.getInstance(context)
        val dao = database.appDao()
        return DataRepository.getInstance(apiService, dao)
    }
}