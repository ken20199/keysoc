package fortress.fortressapp.module.network

import io.reactivex.Observable
import io.reactivex.Scheduler
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.Type

@Suppress("UNCHECKED_CAST")
class ObserveOnCallAdapterFactory(private val scheduler: Scheduler) : CallAdapter.Factory() {

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        if (CallAdapter.Factory.getRawType(returnType) != Observable::class.java) {
            return null
        }
        val delegate = retrofit.nextCallAdapter(this, returnType, annotations) as CallAdapter<Any, Observable<*>>
        return object : CallAdapter<Any, Any> {
            override fun adapt(call: Call<Any>): Any {
                return delegate.adapt(call).observeOn(scheduler)
            }

            override fun responseType(): Type {
                return delegate.responseType()
            }
        }
    }

}
