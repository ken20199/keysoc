package fortress.fortressapp.module.network

import androidx.annotation.NonNull
import com.test.assessment.model.AlbumResponse
import com.test.assessment.network.DEMO_API_SEARCH
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*


interface TestApi {

    @GET(DEMO_API_SEARCH)
    fun getAlbumData(
        @Query ("term") name: String,
        @Query ("entity") album: String
    ): Observable<Response<AlbumResponse>>

}