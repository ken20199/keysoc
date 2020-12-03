package com.test.assessment.network

import android.util.Log
import com.orhanobut.hawk.Hawk
import fortress.fortressapp.module.network.ObserveOnCallAdapterFactory
import fortress.fortressapp.module.network.TestApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object WebServiceModel {

    private val testApi: TestApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiHelper.getInternalAPIDomain())
            .addCallAdapterFactory(ObserveOnCallAdapterFactory(AndroidSchedulers.mainThread()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(NullOnEmptyConverterFactory())  // Handle total empty response
            .addConverterFactory(GsonConverterFactory.create())
            .client(getCustomOkHttpClient())
            .build()
        retrofit.create(TestApi::class.java)
    }


    fun getAlbum(name: String, album: String) = let {
        testApi.getAlbumData(name, album)
    }


}