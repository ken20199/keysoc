package com.test.assessment.network

import android.util.Log
import okhttp3.*
import okio.BufferedSource
import okhttp3.internal.http.HttpHeaders.hasBody
import java.io.IOException
import okhttp3.Interceptor.Chain
import okhttp3.internal.http.HttpHeaders
import okio.Buffer
import java.io.EOFException
import java.nio.charset.Charset
import java.nio.charset.UnsupportedCharsetException
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.JsonElement
import com.orhanobut.logger.Logger
import com.test.assessment.application.MyApplication
import com.test.assessment.utils.DeviceUtils.isNetworkConnected
import okhttp3.ResponseBody


class ErrorHandlerInterceptor : Interceptor {

    companion object {
        var TAG: String = ErrorHandlerInterceptor::class.java.simpleName
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isNetworkConnected(MyApplication.appInstance))
            throw NoConnectivityException()
        else {


            val request = chain.request()
            val response = chain.proceed(request)

            // todo deal with the issues the way you need to
            if (response.code() == 500) {
//                startActivity(
//                    Intent(
//                        this@ErrorHandlingActivity,
//                        ServerIsBrokenActivity::class.java
//                    )
//                )

                Logger.e( "response code  = 500")
                return response
            } else if (response.code() > 200) {
                Logger.e( "response code  = "+response.code())
            }

            return response


//            return chain.proceed(chain.request())
        }


    }

    class NoConnectivityException : Exception() {
        override val message: String?
            get() = "No network available, please check your WiFi or Data connection"
    }
}