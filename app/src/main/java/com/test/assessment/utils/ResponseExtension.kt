package com.infotronic.tech.facade.util

import android.util.Log
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.test.assessment.application.MyApplication
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

/**
 * Extension function to convert error body to Java object if the error code is 400
 * @param type of Java object to be converted
 * @return converted Java object, may be null
 */
fun <T> Throwable.extractErrorResponse(type: Class<T>): T? = run {
    return if (this is HttpException && code() >= 400 && response().errorBody() != null) {
        val body: ResponseBody = response().errorBody()!!
        var response: T? = null
        val gson = Gson()
        val adapter: TypeAdapter<T> = gson.getAdapter(type)

        try {
            response = adapter.fromJson(body.string())
        } catch (e: IOException) {
            e.printStackTrace()
        }
        response
    } else {
        null
    }
}

/**
 * Subscribe to the ObservableSource for onComplete action emitted only
 * use this if knowing the status of success or error is unnecessary
 * @param onComplete will be called regardless of success or error
 * @return a {@link Disposable} reference with which the caller can stop receiving items before
 *         the ObservableSource has finished sending them
 */
fun <T> Observable<Response<T>>.subscribeComplete(
    onComplete: () -> Unit
): Disposable {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({}, {
        it.printStackTrace()
    }, {
        onComplete()
    })
}

/**
 * Subscribe to the ObservableSource for success and error
 * @param onSuccess will be called when the ObservableSource emits the item, thus pass the item through it
 * @param onFailure will be called when the ObservableSoruce throws any error, thus pass the Throwable through it
 * @return a {@link Disposable} reference with which the caller can stop receiving items before
 *         the ObservableSource has finished sending them
 */
fun <T> Observable<Response<T>>.subscribeResponse(
    onSuccess: (Response<T>) -> Unit,
    onFailure: (Throwable) -> Unit
): Disposable {
    lateinit var response: Response<T>
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
        response = it
        onSuccess(it)
    }, {
        if (it is HttpException && it.code() != 401) {
            sendErrorHandler(it, response)
        }
        onFailure(it)
    })
}

private fun <T> sendErrorHandler(thowable: Throwable, response: Response<T>?) {
    response?.let {
        thowable.printStackTrace()
        response.body()?.let { responseBody ->

        }

    }
}



fun sendErrorHandler(
    id: String,
    errorMessage: String?,
    errorCode: String?,
    httpStatusCode: String
) {

}


/**
 * Subscribe to the ObservableSource for success, error and complete
 * @param onSuccess will be called when the ObservableSource emits the item, thus pass the item through it
 * @param onFailure will be called when the ObservableSoruce throws any error, thus pass the Throwable through it
 * @param onComplete will be called regardless of success or error afterwards
 * @return a {@link Disposable} reference with which the caller can stop receiving items before
 *         the ObservableSource has finished sending them
 */
fun <T> Observable<Response<T>>.subscribeAll(
    onSuccess: (Response<T>) -> Unit,
    onFailure: (Throwable) -> Unit,
    onComplete: () -> Unit
): Disposable {
    lateinit var response: Response<T>
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
        response = it
        onSuccess(it)

    }, {
        if (it is HttpException && it.code() != 401) {
            sendErrorHandler(it, response)
        }
        onFailure(it)
    }, {

        onComplete()
    })
}

