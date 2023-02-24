package sahraei.hamidreza.woltpromax.feature.venuelist.data.remote

import com.google.gson.annotations.SerializedName
import okhttp3.internal.filterList
import javax.inject.Inject

class VenueListRemoteDataSource @Inject constructor(
    private val venueListService: VenueListService
) {
    suspend fun getVenueListByCoordinates(lat: Double, lon: Double): List<VenueItem> {
        val section = venueListService.getVenueList(lat, lon).sections.first {
            it.items.any { item ->
                item.venue != null
            }
        }
        val venues = section.items.filterList { venue != null }.mapNotNull { item ->
            if (item.venue != null) {
                VenueItem(
                    id = item.venue.id,
                    name = item.venue.name,
                    shortDescription = item.venue.shortDescription,
                    image = item.image?.url
                )
            } else {
                null
            }
        }.take(LIMIT_SIZE)
        return venues
    }

    companion object {
        private const val LIMIT_SIZE = 15
    }
}

data class VenueItem(
    val id: String,
    val name: String,
    val shortDescription: String?,
    val image: String?,
    var isLiked: Boolean = false
)