package sahraei.hamidreza.woltpromax.feature.venuelist.data

import sahraei.hamidreza.woltpromax.feature.venuelist.data.local.CoordinatesLocalDataSource
import javax.inject.Inject

class VenuesRepository @Inject constructor(
    private val coordinatesLocalDataSource: CoordinatesLocalDataSource
) {

    fun getCoordinates(): List<Pair<Double, Double>> {
        return coordinatesLocalDataSource.coordinatesList
    }
}