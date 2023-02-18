package sahraei.hamidreza.woltpromax.feature.like.data.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import sahraei.hamidreza.woltpromax.feature.like.data.local.dao.LikeVenueDao
import sahraei.hamidreza.woltpromax.feature.like.data.local.entity.LikeVenueEntity
import javax.inject.Inject

class LikeVenueLocalDatasource @Inject constructor(
    private val likeVenueDao: LikeVenueDao
) {

    suspend fun likeVenue(id: String) {
        likeVenueDao.insert(
            LikeVenueEntity(id)
        )
    }

    suspend fun unlikeVenue(id: String) {
        likeVenueDao.delete(
            LikeVenueEntity(id)
        )
    }

    suspend fun all(): Flow<LikeVenueEntity> {
        return flow {
            emit(likeVenueDao.all())
        }
    }

    suspend fun isVenueLiked(id: String): Boolean {
        return likeVenueDao.isVenueLiked(id)
    }
}