package sahraei.hamidreza.woltpromax.feature.like.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "like")
data class LikeVenueEntity(
    @PrimaryKey val id: String
)