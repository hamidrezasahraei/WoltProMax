package sahraei.hamidreza.woltpromax.feature.venuelist.data.remote

import javax.inject.Inject

class VenueListRemoteDataSource @Inject constructor(
    private val venueListService: VenueListService
) {
    suspend fun getVenueListByCoordinates(lat: Double, lon: Double): List<VenueDto> {
        val section = venueListService.getVenueList().sections.first {
            it.items.any { item ->
                item.venue != null
            }
        }
        val venueList = section.items.mapNotNull {
            it.venue
        }
        return venueList
    }
}