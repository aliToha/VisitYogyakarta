package com.muthohhari.visityogyakarta.home

import com.muthohhari.visityogyakarta.Api.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class PresenterHome (private val view: ViewHome) {
    private val subscriptions = CompositeDisposable()

    @NonNull
    var disposable : Disposable? = null

    fun getData() {
        view.showProgressbar()
            disposable = ApiClient.instance.getListPariwisata()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    view.hideProgressbar()
                    view.onSuccess(it)
                }, { it ->
                    view.showProgressbar()
                    view.onError(it.fillInStackTrace())
                })
            subscriptions.add(disposable!!)
        }
}