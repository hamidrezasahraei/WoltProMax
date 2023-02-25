package sahraei.hamidreza.woltpromax.feature.venuelist.data

import kotlinx.coroutines.runBlocking
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
class VenueRepositoryTest {

    private val coordinatesLocalDataSource: CoordinatesLocalDataSource = mock()
    private val likeVenueLocalDatasource: LikeVenueLocalDatasource = mock()
    private val venueListRemoteDataSource: VenueListRemoteDataSource = mock()

    private lateinit var venuesRepository: VenuesRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        venuesRepository = VenuesRepository(
            coordinatesLocalDataSource,
            likeVenueLocalDatasource,
            venueListRemoteDataSource
        )
    }

    @Test
    fun `should return current coordinate from localdatasource`() {
        runBlocking { venuesRepository.getCurrentLocation() }
        verifyBlocking(coordinatesLocalDataSource) { (coordinatesLocalDataSource.currentLocation()) }
    }
}