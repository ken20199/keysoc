package com.test.assessment.application

import android.app.Activity
import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ProcessLifecycleOwner
import com.orhanobut.hawk.Hawk
import com.orhanobut.logger.PrettyFormatStrategy

import org.json.JSONObject


class MyApplication : Application() {

    var topActivityName = ""

    companion object {
        lateinit var appInstance: MyApplication
    }


    override fun onCreate() {
        super.onCreate()
        appInstance = this
        Hawk.init(this).build()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }


    fun isTopActivity(name: String): Boolean {
        return name == topActivityName
    }


}