package com.example.coincap

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CryptoCurrencyViewModel  : ViewModel() {

    private var _cryptos = MutableLiveData<List<CryptoItem>>()
    val cryptos: LiveData<List<CryptoItem>> = _cryptos

    init {
        getAllCryptos ()
    }

    private fun getAllCryptos() {
        viewModelScope.launch {
            try {
                val response = CryptoApi.retrofitService.getCryptos()
                _cryptos.value = response.cryptoList
            } catch (e: Exception) {
                Log.e("ddk9499", e.message.orEmpty())
                _cryptos.value = emptyList()
            }
        }
    }
}