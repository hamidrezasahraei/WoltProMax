package sahraei.hamidreza.woltpromax.feature.venuelist.data.remote

import javax.inject.Inject

class VenueListRemoteDataSource @Inject constructor(
    private val venueListService: VenueListService
) {
    suspend fun getVenueListByCoordinates(lat: Double, lon: Double): VenueListDto {
        return venueListService.getVenueList()
    }
}