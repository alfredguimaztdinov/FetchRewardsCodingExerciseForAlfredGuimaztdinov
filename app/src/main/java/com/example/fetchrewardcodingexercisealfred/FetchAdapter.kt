package com.example.fetchrewardcodingexercisealfred

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import com.example.fetchrewardcodingexercisealfred.databinding.ItemDisplayBinding
import java.util.*
import com.example.fetchrewardcodingexercisealfred.databinding.ActivityMainBinding
import java.util.Objects.compare

class FetchAdapter: RecyclerView.Adapter<FetchAdapter.FetchViewHolder>() {

    inner class FetchViewHolder( val binding: ItemDisplayBinding): RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object :  DiffUtil.ItemCallback<Item>(){
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)


    var items : List<Item>
      get() =  differ.currentList
        set(value) {differ.submitList(value)}




    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FetchViewHolder {
        return FetchViewHolder(ItemDisplayBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))

    }


    override fun onBindViewHolder(holder: FetchViewHolder, position: Int) {


        holder.binding.apply{

            var sortedListId = items.sortedByDescending { it.listId }
            val item = sortedListId[position]


            if (item.listId.toString().isNullOrBlank()|| item.name.isNullOrBlank()){
                tvList.isVisible = false
                tvName.isVisible = false

            }else {


                tvList.text = item.listId.toString()
                tvName.text = item.name

            }

        }
    }



    }
