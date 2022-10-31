package com.example.coincap

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CryptoDao {
    @Query("SELECT * FROM crypto_entity")
    fun getAllCryptos(): LiveData<List<CryptoEntity>>

    @Insert
        (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCryptos(cryptos: List<CryptoEntity>)
}