package com.dacha.taskmanager1.ui.task

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.Resource
import com.dacha.taskmanager1.R
import com.dacha.taskmanager1.data.Tasks
import com.dacha.taskmanager1.databinding.ItemTaskBinding

class TaskAdapter(private var context: Context): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private var task= arrayListOf<Tasks>()
    private lateinit var binding: ItemTaskBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    fun addTask(tasks: Tasks){
        task.add(0, tasks)
        notifyItemChanged(0)

    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() =task.size

    inner class TaskViewHolder(private var binding: ItemTaskBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(){
            if(adapterPosition % 2 == 0)
            {
                binding.coRes.setBackgroundColor(ContextCompat.getColor(context, R.color.black))
                binding.title.setTextColor(ContextCompat.getColor(context , R.color.white))
                binding.desc.setTextColor(ContextCompat.getColor(context , R.color.white))
            }
            else
            {
                binding.title.setTextColor(ContextCompat.getColor(context , R.color.white))
                binding.coRes.setBackgroundColor(ContextCompat.getColor(context , R.color.white))
                binding.desc.setTextColor(ContextCompat.getColor(context , R.color.white))
            }

            val item = task[adapterPosition]
            binding.title.text = item.title
            binding.desc.text = item.desc
        }
    }

}