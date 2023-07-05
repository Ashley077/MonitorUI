package com.example.myapplication.manager

import com.example.myapplication.BuildConfig
import com.example.myapplication.service.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 提供 retrofit 創建出來的 service
 *
 * @author <a href='https://github.com/Ashley077'>Ashley</a>
 */
class RemoteClientManager private constructor() {
    private val retrofit: Retrofit
    private val apiService: ApiService
    private val okHttpClient: OkHttpClient = OkHttpClient()

    init {
        retrofit = Retrofit
            .Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    companion object {
        private val manager = RemoteClientManager()

        /**
         * 提供 ApiService 實例
         *
         *  @author <a href='https://github.com/Ashley077'>Ashley</a>
         */
        val apiServiceClient = manager.apiService
    }
}