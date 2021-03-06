package de.sebschaef.cat.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import de.sebschaef.cat.model.persistence.Image

@Database(entities = [Image::class], version = 1, exportSchema = false)
abstract class ImagesDatabase : RoomDatabase() {

    abstract fun imagesDao(): ImagesDao

}