package sahraei.hamidreza.woltpromax.feature.venuelist.data.remote

import retrofit2.http.GET

interface VenueListService {
    @GET("pages/restaurants?lat=60.170187&lon=24.930599")
    suspend fun getVenueList(): VenueListDto
}