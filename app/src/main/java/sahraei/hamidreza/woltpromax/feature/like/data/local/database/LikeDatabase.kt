package sahraei.hamidreza.woltpromax.feature.like.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import sahraei.hamidreza.woltpromax.feature.like.data.local.LikeVenueEntity
import sahraei.hamidreza.woltpromax.feature.like.data.local.dao.LikeVenueDao

@Database(entities = [LikeVenueEntity::class], version = 1)
abstract class LikeDatabase : RoomDatabase() {
    abstract fun likeVenueDao(): LikeVenueDao
}