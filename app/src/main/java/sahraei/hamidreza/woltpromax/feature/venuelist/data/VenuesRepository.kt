package sahraei.hamidreza.woltpromax.feature.venuelist.data

import kotlinx.coroutines.flow.Flow
import sahraei.hamidreza.woltpromax.feature.like.data.local.LikeVenueLocalDatasource
import sahraei.hamidreza.woltpromax.feature.like.data.local.entity.LikeVenueEntity
import sahraei.hamidreza.woltpromax.feature.venuelist.data.local.CoordinatesLocalDataSource
import sahraei.hamidreza.woltpromax.feature.venuelist.data.remote.VenueDto
import sahraei.hamidreza.woltpromax.feature.venuelist.data.remote.VenueItem
import sahraei.hamidreza.woltpromax.feature.venuelist.data.remote.VenueListDto
import sahraei.hamidreza.woltpromax.feature.venuelist.data.remote.VenueListRemoteDataSource
import javax.inject.Inject

class VenuesRepository @Inject constructor(
    private val coordinatesLocalDataSource: CoordinatesLocalDataSource,
    private val likeVenueLocalDatasource: LikeVenueLocalDatasource,
    private val venueListRemoteDataSource: VenueListRemoteDataSource
) {

    fun getCoordinates(): List<Pair<Double, Double>> {
        return coordinatesLocalDataSource.coordinatesList
    }

    suspend fun isVenueLiked(id: String): Boolean {
        return likeVenueLocalDatasource.isVenueLiked(id)
    }

    suspend fun likeVenue(id: String) {
        likeVenueLocalDatasource.likeVenue(id)
    }

    suspend fun unlikeVenue(id: String) {
        likeVenueLocalDatasource.unlikeVenue(id)
    }

    suspend fun getVenueListByCoordinates(lat: Double, lon: Double): List<VenueItem> {
        val venues = venueListRemoteDataSource.getVenueListByCoordinates(lat, lon)
        venues.forEach { venue ->
            venue.isLiked = likeVenueLocalDatasource.isVenueLiked(venue.id)
        }
        return venues
    }
}