package com.djawnstj.http.person.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.djawnstj.http.databinding.HolderPersonListBinding
import com.djawnstj.http.person.dto.Person

class PersonListAdapter: ListAdapter<Person, PersonListAdapter.ViewHolder>(diffUtil) {

    override fun getItemViewType(position: Int): Int = position

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(HolderPersonListBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindItem(getItem(position), position)

    inner class ViewHolder(private val binding: HolderPersonListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: Person, position: Int) = with(binding) {
            name.text = item.name
            age.text = item.age.toString()
            address.text = item.address
        }
    }

    companion object {
        private val diffUtil: DiffUtil.ItemCallback<Person> = object: DiffUtil.ItemCallback<Person>() {
            override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean = oldItem == newItem

        }
    }
}