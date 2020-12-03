package com.test.assessment.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.test.assessment.constract.ENABLE_TRUST_ALL_SSL_CERT
import fortress.fortressapp.module.network.ConnectionInterceptor
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.net.CookieManager
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import javax.security.cert.CertificateException


const val CONNECTION_TIMEOUT = 60L

fun getCustomOkHttpClient(): OkHttpClient {
    val client = OkHttpClient()
    val builder = client.newBuilder()

    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY


    builder.addNetworkInterceptor(StethoInterceptor())
        .addInterceptor(HeaderInterceptor())
        .addInterceptor(ConnectionInterceptor())
        .addInterceptor(interceptor)
        .addInterceptor(ErrorHandlerInterceptor())

    return genClientBuilder(builder)
}




fun genClientBuilder(builder: OkHttpClient.Builder): OkHttpClient {
    builder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
    builder.readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
    builder.writeTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)

    val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
        @Throws(CertificateException::class)
        override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
        }

        @Throws(CertificateException::class)
        override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
        }

        override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
            return arrayOf()
        }
    })

    if (ENABLE_TRUST_ALL_SSL_CERT ) {
        try {
            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
            //                sslContext.init(null, new TrustManager[]{}, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory

            builder.sslSocketFactory(sslSocketFactory)
            builder.hostnameVerifier { hostname, session -> true }
        } catch (e: KeyManagementException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

    }

    return builder.build()
}