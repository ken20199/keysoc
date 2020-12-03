package com.infotronic.tech.facade.base

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import javax.annotation.OverridingMethodsMustInvokeSuper

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {



    val allDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    val loading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    open fun onRequestStart(){ //default action
        loading.postValue(true)
    }

    open fun onRequestError(msg: String?) { //default action
        Log.d("onRequestError","$msg")
        onRequestFinish()
    }

    open fun onRequestFinish() { //default action
        loading.postValue(false)
    }


    @OverridingMethodsMustInvokeSuper
    override fun onCleared() {
        super.onCleared()
        allDisposable.clear()
    }
}