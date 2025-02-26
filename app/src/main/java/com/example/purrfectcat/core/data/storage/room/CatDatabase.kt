package com.example.purrfectcat.core.data.storage.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.purrfectcat.core.data.storage.models.CatEntity

@Database(entities = [CatEntity::class], version = 1, exportSchema = false)
abstract class CatDatabase: RoomDatabase() {

    companion object{
        @Volatile
        private var instance: CatDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?:
        synchronized(LOCK){
            instance ?:
            createDatabase(context).also{
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CatDatabase::class.java,
                "Cats_db"
            ).build()
    }

    abstract fun getCatDao(): CatDao
}