package com.example.myapplication.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Tasks
import com.example.myapplication.screen.detail.TaskRec
import kotlinx.android.synthetic.main.task_item.view.*

class TaskAdapter(var id: Int) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    var taskNote = emptyList<Tasks>()

    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
    @SuppressLint("NotifyDataSetChanged")
    fun setList(taskNote: List<Tasks>, id:Int) {
        this.taskNote = taskNote
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return taskNote.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        Log.d ("datafrombaza",taskNote.toString())
        holder.itemView.taskName.text = taskNote.get(position).name
        holder.itemView.dateTask.text="до "+taskNote.get(position).date
        val spannableString=SpannableString("Приоритет: "+taskNote.get(position).category)
       if (taskNote.get(position).category.equals("Высокий")){
           spannableString.setSpan(ForegroundColorSpan(Color.RED),11,18,Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        }
        if (taskNote.get(position).category.equals("Средний")){
            spannableString.setSpan(ForegroundColorSpan(Color.YELLOW),11,18,Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        }
        if (taskNote.get(position).category.equals("Низкий")){
            spannableString.setSpan(ForegroundColorSpan(Color.GREEN),11,17,Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        }
        holder.itemView.taskStatus.text=spannableString
if (taskNote.get(position).check==true){
holder.itemView.checkBox.isChecked=true
}else
{
    holder.itemView.checkBox.isChecked=false
}
    }



    override fun onViewAttachedToWindow(holder: TaskViewHolder) {
        super.onViewAttachedToWindow(holder)
      holder.itemView.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
          if (isChecked){
              taskNote[holder.adapterPosition].check=true
          TaskRec.checkTask(taskNote[holder.adapterPosition])
      }else{
              taskNote[holder.adapterPosition].check=false
              TaskRec.checkTask(taskNote[holder.adapterPosition])

          }
    }}
}