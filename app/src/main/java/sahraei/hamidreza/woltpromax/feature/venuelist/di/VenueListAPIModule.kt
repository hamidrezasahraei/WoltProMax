package sahraei.hamidreza.woltpromax.feature.venuelist.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sahraei.hamidreza.woltpromax.feature.venuelist.data.remote.VenueListService

@Module
@InstallIn(SingletonComponent::class)
object VenueListAPIModule {

    private const val BASE_URL = "https://restaurant-api.wolt.com/v1/"

    @Reusable
    @Provides
    fun providesVenueListService(retrofit: Retrofit) =
        retrofit.create(VenueListService::class.java)
}