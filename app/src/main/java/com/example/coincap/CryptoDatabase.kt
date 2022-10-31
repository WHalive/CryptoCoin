package com.example.coincap


import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        CryptoEntity::class
    ],
    version = 1
)

abstract class CryptoDatabase : RoomDatabase(){
    abstract fun cryptoDao(): CryptoDao

    companion object {
        private var INSTANCE: CryptoDatabase? = null

        fun createInstance(context: Context) {
            if (INSTANCE != null) return

            synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    CryptoDatabase::class.java,
                    "crypto_database"
                ).build()
            }
        }

        fun getInstance(): CryptoDatabase {
            return INSTANCE!!
        }
    }
}

