package com.example.coincap

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CryptoCurrencyViewModel : ViewModel() {

//    private var _cryptos = MutableLiveData<List<CryptoItem>>()
//    val cryptos: LiveData<List<CryptoItem>> = _cryptos

    private val repository: CryptoRepository
    private var _cryptos = MutableLiveData<List<CryptoEntity>>()
    var cryptos: LiveData<List<CryptoEntity>> = _cryptos

    init {
        val cryptoDatabase = CryptoDatabase.getInstance().cryptoDao()
        repository = CryptoRepository(cryptoDatabase)
        cryptos = repository.getAllCryptos()
        getAllCryptoItems()
    }

    private fun getAllCryptoItems() {
        viewModelScope.launch {
            try {
//                val response = CryptoApi.retrofitService.getCryptos()
//                _cryptos.value = response.cryptoList
//                _cryptos.value = CryptoApi.retrofitService.getCryptos()
                repository.insertItems()
            } catch (e: Exception) {
                Log.e("ddk9499", e.message.orEmpty())
                _cryptos.value = emptyList()
            }
        }
    }
}