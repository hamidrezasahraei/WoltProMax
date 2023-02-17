package sahraei.hamidreza.woltpromax.feature.venuelist.data

import sahraei.hamidreza.woltpromax.feature.venuelist.data.local.CoordinatesLocalDataSource
import sahraei.hamidreza.woltpromax.feature.venuelist.data.remote.VenueDto
import sahraei.hamidreza.woltpromax.feature.venuelist.data.remote.VenueListDto
import sahraei.hamidreza.woltpromax.feature.venuelist.data.remote.VenueListRemoteDataSource
import javax.inject.Inject

class VenuesRepository @Inject constructor(
    private val coordinatesLocalDataSource: CoordinatesLocalDataSource,
    private val venueListRemoteDataSource: VenueListRemoteDataSource
) {

    fun getCoordinates(): List<Pair<Double, Double>> {
        return coordinatesLocalDataSource.coordinatesList
    }

    suspend fun getVenueListByCoordinates(lat: Double, lon: Double): List<VenueDto> {
        return venueListRemoteDataSource.getVenueListByCoordinates(lat, lon)
    }
}