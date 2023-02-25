package sahraei.hamidreza.woltpromax.feature.venuelist.mapper

import sahraei.hamidreza.woltpromax.feature.venuelist.data.remote.ItemDto
import sahraei.hamidreza.woltpromax.feature.venuelist.model.VenueItem

fun ItemDto.toVenueItem(isLiked: Boolean): VenueItem? {
    return if (venue != null) {
        VenueItem(
            id = venue.id,
            name = venue.name,
            shortDescription = venue.shortDescription,
            image = image?.url,
            isLiked = isLiked
        )
    } else {
        null
    }
}