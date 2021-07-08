package uz.yusufbek_ibragimov.experimentapi.ui.fragments.home_fragment

import uz.yusufbek_ibragimov.experimentapi.core.model.news_model.NewsResponse
import uz.yusufbek_ibragimov.experimentapi.core.model.news_model.NewsResponseItem
import uz.yusufbek_ibragimov.experimentapi.core.network.NetworkHalper

/**
 * Bismillah
 * Ibragimov Yusufbek
 * 07.07.2021
 * ibragimovy390@gmail.com
 * */

class HomeRepository {

    private val networkApi=NetworkHalper.getInstance().getAPI()

    suspend fun loadNews(): NewsResponse = networkApi.getNews()

    suspend fun addNewsItem(newsResponseItem: NewsResponseItem) = networkApi.addNews(newsResponseItem)

    suspend fun editNewsItem(newsResponseItem: NewsResponseItem) = networkApi.editNewsItem(newsResponseItem.id.toInt(),newsResponseItem)

}