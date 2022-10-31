package com.example.coincap

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CryptoRepository(private val cryptoDao: CryptoDao) {
//    suspend fun insertItems(cryptoEntities: List<CryptoEntity>) =
//        cryptoDao.insertCryptos(cryptoEntities)

    fun getAllCryptos(): LiveData<List<CryptoEntity>> =
        cryptoDao.getAllCryptos()

    suspend fun insertItems() {
        withContext(Dispatchers.IO) {
            launch {
                try {
                    val landingEntities = CryptoApi.retrofitService.getCryptos().data
                    cryptoDao.insertCryptos(landingEntities)
//                    Log.d("loading data", "loadLandings: ${CryptoApi.retrofitService.getCryptos()}")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}