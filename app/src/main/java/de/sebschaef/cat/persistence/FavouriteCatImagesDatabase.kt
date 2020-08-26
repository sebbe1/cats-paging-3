package de.sebschaef.cat.persistence

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import de.sebschaef.cat.CatApplication
import de.sebschaef.cat.model.persistence.Image

@Database(entities = [Image::class], version = 1, exportSchema = false)
abstract class FavouriteCatImagesDatabase : RoomDatabase() {

    abstract fun favouriteCatImagesDao(): FavouriteCatImagesDao

    companion object {
        val instance = Room.databaseBuilder(
            CatApplication.appContext,
            FavouriteCatImagesDatabase::class.java,
            "favourite_cat_database"
        ).build()
    }

}