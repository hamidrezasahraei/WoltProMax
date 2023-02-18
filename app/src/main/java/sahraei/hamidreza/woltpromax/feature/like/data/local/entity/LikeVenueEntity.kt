package sahraei.hamidreza.woltpromax.feature.like.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "like")
data class LikeVenueEntity(
    @PrimaryKey val id: String
)