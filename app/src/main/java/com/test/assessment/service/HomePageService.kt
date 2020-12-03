package com.test.assessment.service

import android.util.Log
import com.test.assessment.model.AlbumResponse
import com.test.assessment.model.Result

object HomePageService {

    @JvmStatic
    fun genLoadingList():List<Result>{
        var result = arrayListOf<Result>()
        for (i in 1..10) {
            result.add(Result())
        }
        return result
    }

    fun genRealDisplayList(response: AlbumResponse):List<Result>{
        var result = arrayListOf<Result>()
        response.results.forEach {
            result.add(it)
        }

        result.forEach {
            Log.d(this.javaClass.simpleName,"result list at service = "+it)
        }

        return result

    }
}