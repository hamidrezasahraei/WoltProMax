package sahraei.hamidreza.woltpromax.feature.venuelist.data

import sahraei.hamidreza.woltpromax.feature.like.data.local.LikeVenueLocalDatasource
import sahraei.hamidreza.woltpromax.feature.venuelist.data.local.CoordinatesLocalDataSource
import sahraei.hamidreza.woltpromax.feature.venuelist.data.remote.ItemDto
import sahraei.hamidreza.woltpromax.feature.venuelist.data.remote.VenueListRemoteDataSource
import javax.inject.Inject

class VenuesRepository @Inject constructor(
    private val coordinatesLocalDataSource: CoordinatesLocalDataSource,
    private val likeVenueLocalDatasource: LikeVenueLocalDatasource,
    private val venueListRemoteDataSource: VenueListRemoteDataSource
) {

    fun getCurrentLocation(): Pair<Double, Double> {
        return coordinatesLocalDataSource.currentLocation()
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

    suspend fun getVenueListByCoordinates(lat: Double, lon: Double): List<ItemDto> {
        return venueListRemoteDataSource.getVenueListByCoordinates(lat, lon)
    }
}