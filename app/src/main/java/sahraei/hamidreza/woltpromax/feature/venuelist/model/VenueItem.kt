package sahraei.hamidreza.woltpromax.feature.venuelist.model

data class VenueItem(
    val id: String,
    val name: String,
    val shortDescription: String?,
    val image: String?,
    var isLiked: Boolean = false
)