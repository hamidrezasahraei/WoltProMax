package sahraei.hamidreza.woltpromax.feature.venuelist.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sahraei.hamidreza.woltpromax.feature.venuelist.data.VenuesRepository
import sahraei.hamidreza.woltpromax.feature.venuelist.data.remote.VenueItem
import javax.inject.Inject

@HiltViewModel
class VenueListViewModel @Inject constructor(
    private val venuesRepository: VenuesRepository
) : ViewModel() {

    var state by mutableStateOf(VenueListScreenState())
        private set

    private val venueList = mutableStateListOf<VenueItem>()

    init {
        getVenueList()
    }

    private fun getVenueList() {
        viewModelScope.launch {
            venueList.addAll(venuesRepository.getVenueListByCoordinates(10.0, 10.0))
            state = state.copy(
                isLoading = false,
                venues = venueList
            )
        }
    }

    fun onLikedClicked(id: String) {
        viewModelScope.launch {
            val isLikedBefore = venuesRepository.isVenueLiked(id)
            if (isLikedBefore) {
                venuesRepository.unlikeVenue(id)
            } else {
                venuesRepository.likeVenue(id)
            }
            val index = venueList.indexOfFirst { it.id == id }
            venueList[index] = venueList[index].copy(isLiked = !isLikedBefore)
        }
    }
}

data class VenueListScreenState(
    val isLoading: Boolean = true,
    val venues: List<VenueItem>? = null
)