package uz.yusufbek_ibragimov.experimentapi.ui.fragments.home_fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.yusufbek_ibragimov.experimentapi.core.model.news_model.NewsResponse
import uz.yusufbek_ibragimov.experimentapi.core.model.news_model.NewsResponseItem

/**
 * Bismillah
 * Ibragimov Yusufbek
 * 07.07.2021
 * ibragimovy390@gmail.com
 * */

class HomeVM : ViewModel() {

    private val homeRepository=HomeRepository()

    private val _newsLiveData = MutableLiveData<NewsResponse>()
    val newsLiveData: LiveData<NewsResponse> get() = _newsLiveData

    fun getNews(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                try {
                    val response=homeRepository.loadNews()
                    _newsLiveData.postValue(response)
                    Log.d("MyTAG", "getNewsNetwork: $response")
                }catch (e:Exception){
                    Log.d("MyTAG", "getNewsDb: $e")
                }
            }
        }
    }

    fun addNews(newsResponseItem: NewsResponseItem){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                try {
                    homeRepository.addNewsItem(newsResponseItem)
                }catch (e:Exception){
                }
            }
        }
    }

    private val _editLiveData = MutableLiveData<Boolean>()
    val editLiveData: LiveData<Boolean> get() = _editLiveData

    fun editNews(id:Int,newsResponseItem: NewsResponseItem){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                try {
                    homeRepository.editNewsItem(newsResponseItem)
                    _editLiveData.postValue(true)
                }catch (e:Exception){
                    _editLiveData.postValue(false)
                }

            }
        }
    }


}