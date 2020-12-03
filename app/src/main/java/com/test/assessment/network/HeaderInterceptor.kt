package com.test.assessment.network

import android.os.Build
import com.orhanobut.hawk.Hawk
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Harris.C on 8/3/2019.
 */
class HeaderInterceptor(): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var newRequest =

            request.newBuilder()
            .addHeader("Content-Type", "application/json")


            if (Hawk.contains("ROUTEID")){
                newRequest.addHeader("ROUTEID",Hawk.get("ROUTEID"))

            }

        return chain.proceed(newRequest.build())
    }
}