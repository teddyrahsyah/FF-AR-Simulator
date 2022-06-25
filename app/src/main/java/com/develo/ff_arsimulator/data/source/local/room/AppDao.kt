package com.develo.ff_arsimulator.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.develo.ff_arsimulator.data.source.local.entity.ArticleEntity
import com.develo.ff_arsimulator.data.source.local.entity.LabEntity
import com.develo.ff_arsimulator.data.source.local.entity.ModuleEntity
import com.develo.ff_arsimulator.data.source.local.entity.TheoryEntity
import com.develo.ff_arsimulator.data.source.local.entity.relations.ModuleWithTheoryWithLab

@Dao
interface AppDao {

    // Module, Theory, and Lab Query
    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = ModuleEntity::class)
    suspend fun insertModules(module: List<ModuleEntity>)
    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TheoryEntity::class)
    suspend fun insertTheories(theories: List<TheoryEntity>)
    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = LabEntity::class)
    suspend fun insertLabs(labs: List<LabEntity>)

    @Query("DELETE FROM modules")
    suspend fun deleteModules()
    @Query("DELETE FROM theories")
    suspend fun deleteTheories()
    @Query("DELETE FROM labs")
    suspend fun deleteLabs()

    @Transaction
    @Query("SELECT * FROM modules")
    fun getModuleWithTheoryWithLab(): LiveData<List<ModuleWithTheoryWithLab>>
    @Transaction
    @Query("SELECT * FROM theories where id = :id")
    fun getTheoryDetail(id: String): LiveData<TheoryEntity>
    @Transaction
    @Query("SELECT * FROM labs where id = :id")
    fun getLabDetail(id: String): LiveData<LabEntity>

    // Article Query
    @Query("SELECT * FROM articles")
    fun getArticles(): LiveData<List<ArticleEntity>>
    @Query("SELECT * FROM articles WHERE id = :id")
    fun getArticleDetail(id: String): LiveData<ArticleEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE, entity = ArticleEntity::class)
    suspend fun insertArticles(articles: List<ArticleEntity>)

    @Query("DELETE FROM articles")
    suspend fun deleteArticles()
}