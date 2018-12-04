package com.muthohhari.visityogyakarta.Api

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.muthohhari.visityogyakarta.BuildConfig
import com.muthohhari.visityogyakarta.models.Pariwisata
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private val myAppService: DataServices

    init {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val clientBuilder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        clientBuilder.addInterceptor(loggingInterceptor)

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        myAppService = retrofit.create(DataServices::class.java)
    }

    companion object {
        private val BASE_URL = BuildConfig.BASE_URL
        private var apiClient: ApiClient? = null

        val instance: ApiClient
            get() {
            if (apiClient == null) {
                apiClient =
                        ApiClient()
            }
            return apiClient as ApiClient
        }
    }

    fun getListPariwisata(): Observable<Response<Pariwisata>> {
        return myAppService.getList()
    }
}