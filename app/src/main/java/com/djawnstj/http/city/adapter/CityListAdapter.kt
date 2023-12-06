package com.djawnstj.http.city.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.djawnstj.http.city.dto.City
import com.djawnstj.http.databinding.HolderCityListBinding

class CityListAdapter: ListAdapter<City, CityListAdapter.ViewHolder>(diffUtil) {

    fun interface OnCityHolderClickListener {
        fun clickCityHolder(id: Long?)
    }

    private var cityHolderClickListener: OnCityHolderClickListener? = null

    fun setOnCityHolderClickListener(listener: OnCityHolderClickListener) {
        this.cityHolderClickListener = listener
    }

    override fun getItemViewType(position: Int): Int = position

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(HolderCityListBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindItem(getItem(position), position)

    inner class ViewHolder(private val binding: HolderCityListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: City, position: Int) = with(binding) {
            root.setOnClickListener {
                cityHolderClickListener?.clickCityHolder(item.id)
            }
            name.text = item.name
            code.text = item.code.toString()
        }
    }

    companion object {
        private val diffUtil: DiffUtil.ItemCallback<City> = object: DiffUtil.ItemCallback<City>() {
            override fun areItemsTheSame(oldItem: City, newItem: City): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: City, newItem: City): Boolean = oldItem == newItem

        }
    }
}