package sahraei.hamidreza.woltpromax.feature.like.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import sahraei.hamidreza.woltpromax.feature.like.data.local.dao.LikeVenueDao
import sahraei.hamidreza.woltpromax.feature.like.data.local.database.LikeDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LikeDatabaseModule {

    @Provides
    @Singleton
    fun provideLikeDatabase(
        @ApplicationContext context: Context
    ): LikeDatabase {
        return Room.databaseBuilder(
            context,
            LikeDatabase::class.java,
            "WoltProMax.db"
        ).build()
    }

    @Provides
    fun provideFeedDao(
        likeDatabase: LikeDatabase
    ): LikeVenueDao {
        return likeDatabase.likeVenueDao()
    }
}