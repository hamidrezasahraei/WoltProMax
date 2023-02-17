package sahraei.hamidreza.woltpromax.feature.venuelist.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import sahraei.hamidreza.woltpromax.ui.theme.Typography

@Composable
fun VenueListScreen() {
    LazyColumn {
        items(10) {
            CardItem(title = "TestTitle") {

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
    onLikeClicked: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onLikeClicked.invoke()
            },
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
        }
    }
}