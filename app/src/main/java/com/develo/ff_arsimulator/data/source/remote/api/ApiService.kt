package com.develo.ff_arsimulator.data.source.remote.api

import com.develo.ff_arsimulator.data.source.local.model.MessageRequest
import com.develo.ff_arsimulator.data.source.remote.response.ArticleResponse
import com.develo.ff_arsimulator.data.source.remote.response.MessageResponse
import com.develo.ff_arsimulator.data.source.remote.response.ModuleResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("module")
    suspend fun getModules(): ModuleResponse

    @GET("article")
    suspend fun getArticles(): ArticleResponse

    @POST("message/create")
    suspend fun postMessage(@Body req: MessageRequest): MessageResponse
}