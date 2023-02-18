package sahraei.hamidreza.woltpromax.feature.venuelist.data.remote

import com.google.gson.annotations.SerializedName

data class VenueListDto(
    @SerializedName("sections") val sections: List<SectionDto>
)

data class SectionDto(
    @SerializedName("items") val items: List<ItemDto>
)

data class ItemDto(
    @SerializedName("venue") val venue: VenueDto? = null,
    @SerializedName("image") val image: ImageDto?,
    )

data class VenueDto(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("short_description") val shortDescription: String?
)

data class ImageDto(
    @SerializedName("url") val url: String
)