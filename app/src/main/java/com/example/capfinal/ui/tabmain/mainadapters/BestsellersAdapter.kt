package com.example.capfinal.ui.tabmain.mainadapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.finalprojectcustomcap.R
import kg.geektech.finalprojectcustomcap.databinding.ItemHomeBestsellersBinding


//Инициализирована типичная моделька для проверки работоспособности адаптера
class BestsellersAdapter : RecyclerView.Adapter<BestsellersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_home_bestsellers, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = 0

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemHomeBestsellersBinding.bind(item)

        fun bind() = with(binding) {
        }
    }
}