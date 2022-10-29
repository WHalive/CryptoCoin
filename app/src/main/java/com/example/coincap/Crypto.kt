package com.example.coincap

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class Crypto(
    @SerializedName("data")
    val cryptoList: List<CryptoItem> = emptyList()
)

data class CryptoItem(
    var id: String = "",
    var rank: Int,
    var symbol: String = "",
    var name: String = "",
    @SerializedName("priceUsd")
    var price:Double,
    var supply: Double,
    var maxSupply: Double
)