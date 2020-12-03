package com.test.assessment.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.infotronic.tech.facade.base.BaseViewModel
import com.infotronic.tech.facade.util.subscribeAll
import com.test.assessment.model.AlbumResponse
import com.test.assessment.model.Result
import com.test.assessment.network.WebServiceModel

class MainContentViewModel(application: Application) : BaseViewModel(application) {

    val albumLiveData = MutableLiveData<AlbumResponse>()
    var results =  MutableLiveData<List<Result>>()
    var v = 0

    companion object {
        private const val TAG = "MainActivityViewModel"
    }

    fun callAlbumApi(name:String,album:String) {
        allDisposable.add(WebServiceModel.getAlbum(name, album).subscribeAll({
            Log.d(this.javaClass.simpleName, "Success - " + it.body())
            if (it.isSuccessful){
                albumLiveData.postValue(it.body())
            }
        }, {
            Log.d(this.javaClass.simpleName, "Fail call = "+it.message)

        }, {

        }))

    }




}