package com.test.assessment.service

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.orhanobut.hawk.Hawk
import com.test.assessment.constract.BOOKMARK_LIST
import com.test.assessment.model.AlbumResponse
import com.test.assessment.model.Result

object HomePageService {

    @JvmStatic
    fun genLoadingList(): List<Result> {
        var result = arrayListOf<Result>()
        for (i in 1..10) {
            result.add(Result())
        }
        return result
    }

    fun saveBookMarkList(result: Result): Boolean {
        if (Hawk.isBuilt()) {

            var saveList = arrayListOf<Result>()
            return if (Hawk.contains(BOOKMARK_LIST)) {
                saveList = Hawk.get(BOOKMARK_LIST, arrayListOf())
                saveList.add(result)
                Hawk.put(BOOKMARK_LIST, saveList)
                true
            } else {
                saveList.add(result)

                Hawk.put(BOOKMARK_LIST, saveList)

                true
            }

        } else {

            return false
        }

        return false
    }

    fun checkBookmarkList(result: ArrayList<Result>):ArrayList<Result> {
        var list = arrayListOf<Result>()
        if (Hawk.isBuilt()) {
            list = Hawk.get(BOOKMARK_LIST, arrayListOf())
            list.forEach {
                result.forEach { it2 ->
                    if (it.collectionId == it2.collectionId){
                        it2.isBookmark = true
                    }
                }
            }
        }
        return result
    }

}