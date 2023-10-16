package com.ikapurwanti.makeupproduct.data.network.api.service

import com.ikapurwanti.makeupproduct.BuildConfig
import com.ikapurwanti.makeupproduct.data.network.api.model.MakeUpResponse
import com.ikapurwanti.makeupproduct.data.network.api.model.MakeUpResponseItem
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface MakeUpService {

    @GET("api/v1/products.json")
    suspend fun getMakeUp(): List<MakeUpResponseItem>

    companion object {
        @JvmStatic
        operator fun invoke(): MakeUpService {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(MakeUpService::class.java)
        }
    }
}