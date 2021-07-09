package uz.yusufbek_ibragimov.experimentapi.core.network

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import uz.yusufbek_ibragimov.experimentapi.core.model.news_model.NewsResponse
import uz.yusufbek_ibragimov.experimentapi.core.model.news_model.NewsResponseItem


/**
 * Bismillah
 * Ibragimov Yusufbek
 * 07.07.2021
 * ibragimovy390@gmail.com
 * */

interface NewsAPI {

    @GET("api/news")
    suspend fun getNews():NewsResponse

    @POST("api/news")
    suspend fun addNews(
        @Body newsResponseItem: NewsResponseItem
    ): Any

    @PUT("api/news/{id}")
    suspend fun editNewsItem(
        @Path("id") id: Int,
        @Body newsResponseItem: NewsResponseItem
    ): NewsResponseItem

    @Multipart
    @POST("api/news/{id}")
    fun upload(
        @Part("description") description: RequestBody?,
        @Part file: Part?
    ): Call<ResponseBody?>?

}