package com.develo.ff_arsimulator.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.develo.ff_arsimulator.data.source.local.entity.*

@Database(
    entities = [
        ArticleEntity::class,
        ModuleEntity::class,
        TheoryEntity::class,
        LabEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object {
        @Volatile
        private var instance: AppRoomDatabase? = null
        fun getInstance(context: Context): AppRoomDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDatabase::class.java, "arsim.db"
                ).build()
            }
    }
}