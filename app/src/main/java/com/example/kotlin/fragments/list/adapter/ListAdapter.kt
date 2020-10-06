package com.example.kotlin.fragments.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.databinding.RowLayoutBinding
import com.example.kotlin.data.models.ToDoData

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {


     var dataList = emptyList<ToDoData>()

    class ViewHolder(private val binding:RowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(toDoData: ToDoData){
            binding.toDoData = toDoData
            binding.executePendingBindings()
        }


        companion object{
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowLayoutBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(
                    binding
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder.from(
            parent
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.bind(currentItem)
    }

    fun setData(toDoData: List<ToDoData>){
        val todoDiffUtil = ToDoDiffUtil(dataList,toDoData)
        val todoDiffResult = DiffUtil.calculateDiff(todoDiffUtil)

        this.dataList = toDoData
        todoDiffResult.dispatchUpdatesTo(this)
    }

}