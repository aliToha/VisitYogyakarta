package com.muthohhari.visityogyakarta.home

import com.muthohhari.visityogyakarta.models.Pariwisata
import retrofit2.Response

interface ViewHome {

    fun showProgressbar()
    fun hideProgressbar()
    fun onSuccess(respon: Response<Pariwisata>)
    fun onError(throwable: Throwable)
}