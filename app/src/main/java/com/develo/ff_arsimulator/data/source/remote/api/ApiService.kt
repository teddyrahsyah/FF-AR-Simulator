package com.develo.ff_arsimulator.data.source.remote.api

import com.develo.ff_arsimulator.data.source.remote.response.ArticleResponse
import com.develo.ff_arsimulator.data.source.remote.response.ModuleResponse
import retrofit2.http.GET

interface ApiService {

//    @GET("lecture")
//    suspend fun getLectures(): LectureResponse

    @GET("module")
    suspend fun getModules(): ModuleResponse

    @GET("article")
    suspend fun getArticles(): ArticleResponse
}