package com.example.myapplication.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.FilesNote
import com.example.myapplication.screen.start.StartFragment
import kotlinx.android.synthetic.main.list_item.view.*

class FileAdapter:RecyclerView.Adapter<FileAdapter.NoteViewHolder>() {

    var listNote = emptyList<FilesNote>()

    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    }

    override fun getItemCount(): Int {
        return listNote.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.itemView.FileName.text = listNote[position].title
        Log.d("asam",listNote[position].time)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<FilesNote>) {
        listNote = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: NoteViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            StartFragment.clickNote(listNote[holder.adapterPosition].id)

        }
    }


}
