package com.example.coincap

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.coincap.databinding.FragmentCryptoCurrencyBinding
import com.example.coincap.databinding.ItemViewBinding
import java.text.NumberFormat

class CryptoCurrencyFragment : Fragment() {

    companion object {
        fun newInstance() = CryptoCurrencyFragment()
    }

    private lateinit var binding: FragmentCryptoCurrencyBinding
    private val viewModel: CryptoCurrencyViewModel by viewModels()
    private val adapter by lazy { CryptoAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCryptoCurrencyBinding.inflate(inflater, container, false)
        binding.cryptoRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.cryptoRecyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.cryptos.observe(viewLifecycleOwner) { cryptos ->
            Log.d("ddk9499", "onViewCreated: ${cryptos.joinToString()}")
            updateUI(cryptos)
        }
    }

    private fun updateUI(cryptos: List<CryptoItem>) {
        adapter.setCryptos(cryptos)
    }

    private class CryptoAdapter : RecyclerView.Adapter<CryptoAdapter.CryptoHolder>() {

        private val cryptos = mutableListOf<CryptoItem>()

        fun setCryptos(cryptos: List<CryptoItem>) {
            this.cryptos.clear()
            this.cryptos.addAll(cryptos)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
            return CryptoHolder(ItemViewBinding.inflate(LayoutInflater.from(parent.context)))
        }

        override fun onBindViewHolder(holder: CryptoHolder, position: Int) {
            val cryptos = cryptos[position]
            holder.bind(cryptos)
        }

        override fun getItemCount() = cryptos.size

        private inner class CryptoHolder(private var binding: ItemViewBinding) :
            RecyclerView.ViewHolder(binding.root) {


            @SuppressLint("SetTextI18n")
            fun bind(crypto: CryptoItem) {
                val symbol = crypto.symbol.lowercase()
                val modifiedUrl =
                    "https://cryptoicons.org/api/color/$symbol/600"
                binding.cryptoSymbol.text = crypto.symbol
                binding.cryptoName.text = crypto.name
                binding.cryptoIcon.load(modifiedUrl)
                binding.cryptoPrice.text = NumberFormat.getInstance().format(crypto.price)


//                binding.cryptoSupplyNumber.text = crypto.supply.toBigDecimal().toString()
//                binding.cryptoMaxNumber.text = crypto.maxSupply.toBigDecimal().toString()
//                binding.cryptoPrice.text = crypto.price.toString()
//                "https://cryptoicons.org/api/color/btc/600"
            }
        }
    }
}