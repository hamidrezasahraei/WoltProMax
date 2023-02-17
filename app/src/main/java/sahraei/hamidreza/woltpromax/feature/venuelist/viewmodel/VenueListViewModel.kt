package sahraei.hamidreza.woltpromax.feature.venuelist.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sahraei.hamidreza.woltpromax.feature.venuelist.data.VenuesRepository
import sahraei.hamidreza.woltpromax.feature.venuelist.data.remote.VenueDto
import javax.inject.Inject

@HiltViewModel
class VenueListViewModel @Inject constructor(
    private val venuesRepository: VenuesRepository
) : ViewModel() {

    var state by mutableStateOf(VenueListScreenState())
        private set

    init {
        getVenueList()
    }

    private fun getVenueList() {
        viewModelScope.launch {
            val venues = venuesRepository.getVenueListByCoordinates(10.0,10.0)
            state = state.copy(
                isLoading = false,
                venues = venues
            )
        }
    }
}

data class VenueListScreenState(
    val isLoading: Boolean = true,
    val venues: List<VenueDto>? = null
)