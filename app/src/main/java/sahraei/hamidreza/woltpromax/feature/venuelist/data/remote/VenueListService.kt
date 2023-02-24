package sahraei.hamidreza.woltpromax.feature.venuelist.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface VenueListService {
    @GET("pages/restaurants")
    suspend fun getVenueList(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): VenueListDto
}