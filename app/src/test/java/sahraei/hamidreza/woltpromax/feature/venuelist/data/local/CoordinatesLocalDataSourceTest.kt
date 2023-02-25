package sahraei.hamidreza.woltpromax.feature.venuelist.data.local

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyBlocking
import sahraei.hamidreza.woltpromax.feature.like.data.local.LikeVenueLocalDatasource
import sahraei.hamidreza.woltpromax.feature.venuelist.data.local.CoordinatesLocalDataSource
import sahraei.hamidreza.woltpromax.feature.venuelist.data.remote.VenueListRemoteDataSource


@RunWith(MockitoJUnitRunner::class)
class CoordinatesLocalDataSourceTest {

    private lateinit var coordinatesLocalDataSource: CoordinatesLocalDataSource

    @Before
    fun setUp() {
        coordinatesLocalDataSource = CoordinatesLocalDataSource()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should return first current coordinate after the first location call`() = runTest {
        val coordinate = coordinatesLocalDataSource.currentLocation()
        assert(coordinate.first == 60.170187 && coordinate.second == 24.930599)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should return 10th current coordinate after the 10th location call`() = runTest {
        repeat(9) {
            coordinatesLocalDataSource.currentLocation()
        }
        val coordinate = coordinatesLocalDataSource.currentLocation()
        assert(coordinate.first == 60.170085 && coordinate.second == 24.929569)
    }
}