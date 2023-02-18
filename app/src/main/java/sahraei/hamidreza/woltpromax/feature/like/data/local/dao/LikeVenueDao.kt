package sahraei.hamidreza.woltpromax.feature.like.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import sahraei.hamidreza.woltpromax.feature.like.data.local.LikeVenueEntity

@Dao
interface LikeVenueDao {

    @Query("SELECT EXISTS(SELECT * FROM `like` WHERE id = :id)")
    fun isVenueLiked(id : Int) : Boolean

    @Insert(onConflict = REPLACE)
    suspend fun insert(likeVenueEntity: LikeVenueEntity)

    @Delete
    suspend fun delete(likeVenueEntity: LikeVenueEntity)
}