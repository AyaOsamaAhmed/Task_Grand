package  com.aya.taskgrand.core.network

import com.aya.taskgrand.core.response.ErrorResponse
import com.aya.taskgrand.core.response.NetworkResponse
import com.aya.taskgrand.features.fragment.home.data.MatchesResponse
import retrofit2.http.GET


interface MainApis {

    companion object {
        private const val MATCHES = "v2/competitions/2021/matches"

    }

    @GET(MATCHES)
    suspend fun listMatches(): NetworkResponse<MatchesResponse, ErrorResponse>



}