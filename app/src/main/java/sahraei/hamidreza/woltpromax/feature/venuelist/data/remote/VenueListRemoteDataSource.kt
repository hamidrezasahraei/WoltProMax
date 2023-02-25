package sahraei.hamidreza.woltpromax.feature.venuelist.data.remote

import com.google.gson.annotations.SerializedName
import okhttp3.internal.filterList
import javax.inject.Inject

class VenueListRemoteDataSource @Inject constructor(
    private val venueListService: VenueListService
) {
    suspend fun getVenueListByCoordinates(lat: Double, lon: Double): List<ItemDto> {
        val section = venueListService.getVenueList(lat, lon).sections.first {
            it.items.any { item ->
                item.venue != null
            }
        }
        return section.items.filterList { venue != null }.take(LIMIT_SIZE)
    }

    companion object {
        private const val LIMIT_SIZE = 15
    }
}