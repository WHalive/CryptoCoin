package com.example.coincap

import androidx.room.Entity
import androidx.room.PrimaryKey

data class CryptoEntities(val data: List<CryptoEntity>)

@Entity(tableName = "crypto_entity")
data class CryptoEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val symbol: String = "",
    val name: String = "",
    val priceUsd: Double
//    val rank: String,
//    val supply: Double,
//    val maxSupply: Double
)