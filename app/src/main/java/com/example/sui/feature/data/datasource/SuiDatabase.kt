package com.example.sui.feature.data.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sui.feature.domain.model.Sui

@Database(
    entities = [Sui::class],
    version = 1
)

abstract class SuiDatabase : RoomDatabase() {

    abstract val dao: SuiDao

    companion object {
        @Volatile
        private var INSTANCE: SuiDatabase? = null

        fun getDatabase(context: Context): SuiDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SuiDatabase::class.java,
                    "person_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }


}