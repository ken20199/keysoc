package fortress.fortressapp.module.network

import com.test.assessment.application.MyApplication
import com.test.assessment.utils.DeviceUtils.isNetworkConnected
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class ConnectionInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isNetworkConnected(MyApplication.appInstance))
            throw NoConnectivityException()
        else {
            return chain.proceed(chain.request())
        }
    }

    class NoConnectivityException : Exception() {
        override val message: String?
            get() = "No network available, please check your WiFi or Data connection"
    }
}