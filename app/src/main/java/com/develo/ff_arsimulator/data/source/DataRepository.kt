package com.develo.ff_arsimulator.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.develo.ff_arsimulator.data.source.local.entity.*
import com.develo.ff_arsimulator.data.source.local.entity.relations.ModuleWithTheoryWithLab
import com.develo.ff_arsimulator.data.source.local.room.AppDao
import com.develo.ff_arsimulator.data.source.remote.api.ApiService

class DataRepository private constructor(
    private val apiService: ApiService,
    private val appDao: AppDao
) {
    companion object {
        @Volatile
        private var instance: DataRepository? = null
        fun getInstance(
            apiService: ApiService,
            appDao: AppDao
        ): DataRepository =
            instance ?: synchronized(this) {
                instance ?: DataRepository(apiService, appDao)
            }.also { instance = it }
    }

    // Module Data
    fun getModules(): LiveData<Result<List<ModuleWithTheoryWithLab>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getModules()
            val modules = response.results

            val moduleList = modules.map { module ->
                ModuleEntity(
                    module.id,
                    module.moduleNumber,
                    module.moduleTitle
                )
            }
            val theoryList = modules.flatMap { module ->
                module.theory.map { theory ->
                    TheoryEntity(
                        theory.id,
                        theory.moduleId,
                        theory.moduleNumber,
                        theory.moduleTitle,
                        theory.theoryNumber,
                        theory.category,
                        theory.title,
                        theory.description,
                        theory.image,
                    )
                }
            }
            val labList = modules.flatMap { module ->
                module.lab.map { lab ->
                    LabEntity(
                        lab.id,
                        lab.moduleId,
                        lab.moduleNumber,
                        lab.moduleTitle,
                        lab.labNumber,
                        lab.category,
                        lab.title,
                        lab.description,
                        lab.image,
                        lab.modelAR
                    )
                }
            }
            appDao.deleteModules()
            appDao.deleteTheories()
            appDao.deleteLabs()
            appDao.insertModules(moduleList)
            appDao.insertTheories(theoryList)
            appDao.insertLabs(labList)
        } catch (e: Exception) {
            Log.d("DataRepository", "getModules: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
        val localData: LiveData<Result<List<ModuleWithTheoryWithLab>>> =
            appDao.getModuleWithTheoryWithLab().map { Result.Success(it) }
        emitSource(localData)
    }

    fun getTheoryDetail(id: String): LiveData<Result<TheoryEntity>> = liveData {
        val localData: LiveData<Result<TheoryEntity>> =
            appDao.getTheoryDetail(id).map { Result.Success(it) }
        emitSource(localData)
    }

    fun getLabDetail(id: String): LiveData<Result<LabEntity>> = liveData {
        val localData: LiveData<Result<LabEntity>> =
            appDao.getLabDetail(id).map { Result.Success(it) }
        emitSource(localData)
    }

    // Article Data
    fun getArticles(): LiveData<Result<List<ArticleEntity>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getArticles()
            val articles = response.results
            val articlesList = articles.map { article ->
                ArticleEntity(
                    article.id,
                    article.author,
                    article.category,
                    article.title,
                    article.description,
                    article.image,
                    article.date,
                )
            }
            appDao.deleteArticles()
            appDao.insertArticles(articlesList)
        } catch (e: Exception) {
            Log.d("DataRepository", "getArticles: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
        val localData: LiveData<Result<List<ArticleEntity>>> =
            appDao.getArticles().map { Result.Success(it) }
        emitSource(localData)
    }

    fun getArticleDetail(id: String): LiveData<Result<ArticleEntity>> = liveData {
        val localData: LiveData<Result<ArticleEntity>> =
            appDao.getArticleDetail(id).map { Result.Success(it) }
        emitSource(localData)
    }
}