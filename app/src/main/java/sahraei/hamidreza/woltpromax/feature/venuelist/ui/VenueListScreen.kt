package sahraei.hamidreza.woltpromax.feature.venuelist.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import kotlinx.coroutines.delay
import sahraei.hamidreza.woltpromax.common.widget.AnimatedLikeButton
import sahraei.hamidreza.woltpromax.common.widget.WoltProMaxProgressItem
import sahraei.hamidreza.woltpromax.feature.venuelist.data.remote.VenueItem
import sahraei.hamidreza.woltpromax.feature.venuelist.viewmodel.VenueListViewModel
import sahraei.hamidreza.woltpromax.ui.theme.Typography
import java.util.concurrent.TimeUnit

private val TTL = TimeUnit.SECONDS.toMillis(10)

@Composable
fun VenueListScreen(
    venueListViewModel: VenueListViewModel = hiltViewModel()
) {
    val state = venueListViewModel.state

    RepeatTaskComposable(TTL) {
        venueListViewModel.onLocationChanged()
    }
    when {
        state.isLoading -> {
            WoltProMaxProgressItem()
        }
        state.venues != null -> {
            VenuesListSection(state.venues) { id ->
                venueListViewModel.onLikedClicked(id)
            }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VenuesListSection(
    venues: List<VenueItem>,
    onLikedClicked: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = venues,
            key = { it.id }
        ) { item ->
            CardItem(
                modifier = Modifier.animateItemPlacement(),
                title = item.name,
                description = item.shortDescription,
                imageUrl = item.image,
                isLiked = item.isLiked
            ) {
                onLikedClicked.invoke(item.id)
            }
        }
    }
}

@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String? = null,
    imageUrl: String? = null,
    isLiked: Boolean = false,
    onLikeClicked: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        elevation = 8.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
        ) {
            if (!imageUrl.isNullOrEmpty()) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "$title image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(48.dp)
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1.0f)
            ) {
                Text(
                    text = title,
                    style = Typography.body1,
                    color = MaterialTheme.colors.onSurface
                )
                Spacer(modifier = Modifier.size(8.dp))
                if (!description.isNullOrEmpty()) {
                    Text(
                        text = description,
                        style = Typography.subtitle1,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }

            AnimatedLikeButton(
                isChecked = isLiked
            ) {
                onLikeClicked.invoke()
            }
        }
    }
}

@Composable
fun RepeatTaskComposable(periodTime: Long, task: () -> Unit) {
    LaunchedEffect(Unit) {
        while(true) {
            task.invoke()
            delay(periodTime)
        }
    }
}

@Preview
@Composable
fun VenueListScreenPreview() {
    VenueListScreen()
}