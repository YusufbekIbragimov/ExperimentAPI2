package uz.yusufbek_ibragimov.experimentapi.core.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import uz.yusufbek_ibragimov.experimentapi.core.utils.BASE_URL

/**
 * Bismillah
 * Ibragimov Yusufbek
 * 07.07.2021
 * ibragimovy390@gmail.com
 * */

class NetworkHalper private constructor(){

    private lateinit var retrofit:Retrofit

    companion object{

        private var networkHalper:NetworkHalper?=null

        fun getInstance(): NetworkHalper{

            if (networkHalper==null){
                networkHalper= NetworkHalper()
            }

            return networkHalper!!
        }

    }

    init {
        retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient())
            .build()
    }

    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(getLogingInteceptor())
            .build()
    }

    private fun getLogingInteceptor(): Interceptor {
        val interceptor=HttpLoggingInterceptor {
            Timber.i(
                "Lesson33Logging## %s",
                it
            )
        }
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    fun getAPI():NewsAPI{
        return getInstance().retrofit.create(NewsAPI::class.java)
    }

}