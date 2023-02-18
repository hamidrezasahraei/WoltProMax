package sahraei.hamidreza.woltpromax.feature.like.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import sahraei.hamidreza.woltpromax.feature.like.data.local.entity.LikeVenueEntity

@Dao
interface LikeVenueDao {

    @Query("SELECT EXISTS(SELECT * FROM `like` WHERE id = :id)")
    suspend fun isVenueLiked(id : String) : Boolean

    @Query("SELECT * FROM `like`")
    suspend fun all(): LikeVenueEntity

    @Insert(onConflict = REPLACE)
    suspend fun insert(likeVenueEntity: LikeVenueEntity)

    @Delete
    suspend fun delete(likeVenueEntity: LikeVenueEntity)
}