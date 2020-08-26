package de.sebschaef.cat.repository

import de.sebschaef.cat.model.network.FavouriteRequest
import de.sebschaef.cat.model.persistence.Image
import de.sebschaef.cat.network.ApiKeyInterceptor
import de.sebschaef.cat.network.CatService
import de.sebschaef.cat.network.toImageList
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object CatRepository {

    // TODO Dependency injection
    private val catService by lazy {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor())
            .build()

        Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(CatService::class.java)
    }

    suspend fun getRandomCatImages(page: Int, pageSize: Int): List<Image> =
        catService.searchImages(page = 1, limit = pageSize).toImageList()

    suspend fun addFavourite(imageId: String, userId: String) {
        catService.addFavourite(
            FavouriteRequest(imageId = imageId, subId = userId)
        )
    }

    suspend fun getFavourites(userId: String, page: Int, pageSize: Int) =
        catService.getFavourites(
            subId = userId,
            page = page,
            limit = pageSize
        )
            .map { it.image }
            .toImageList()

}