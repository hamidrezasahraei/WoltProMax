package sahraei.hamidreza.woltpromax.feature.venuelist.data.local

import javax.inject.Inject

class CoordinatesLocalDataSource @Inject constructor() {

    private var currentLocationIndex = 0

    private val coordinatesList = listOf(
        Pair(60.170187, 24.930599),
        Pair(60.169418, 24.931618),
        Pair(60.169818, 24.932906),
        Pair(60.170005, 24.935105),
        Pair(60.169108, 24.936210),
        Pair(60.168355, 24.934869),
        Pair(60.167560, 24.932562),
        Pair(60.168254, 24.931532),
        Pair(60.169012, 24.930341),
        Pair(60.170085, 24.929569)
    )

    fun currentLocation(): Pair<Double, Double> {
        return coordinatesList[currentLocationIndex++ % 10]
    }
}