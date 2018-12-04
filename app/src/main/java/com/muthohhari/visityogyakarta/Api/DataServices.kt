package com.muthohhari.visityogyakarta.Api

import com.muthohhari.visityogyakarta.models.Pariwisata
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface DataServices {
    @GET("jsonBootcamp.php")
    fun getList(): Observable<Response<Pariwisata>>
}