package com.example.myapplication.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Node
import com.example.myapplication.screen.detail.RecFrag
import kotlinx.android.synthetic.main.node_item.view.*

class NodeAdapter(var id:Int):RecyclerView.Adapter<NodeAdapter.NoteViewHolder>() {

    var listNote = emptyList<Node>()

    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    }

    override fun getItemCount(): Int {
        return listNote.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.node_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.itemView.taskName.text = listNote[position].name
        holder.itemView.dateTime.text = listNote[position].date
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Node>,id:Int) {
        listNote = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: NoteViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            RecFrag.clickNote(listNote[holder.adapterPosition])

        }
    }


}
