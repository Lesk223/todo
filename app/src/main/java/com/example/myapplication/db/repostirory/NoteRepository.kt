package com.example.myapplication.db.repostirory

import android.app.TaskStackBuilder
import androidx.lifecycle.LiveData
import com.example.myapplication.model.NodeWithTask
import com.example.myapplication.model.NoteModel
import com.example.myapplication.model.Tasks
import org.w3c.dom.Node

interface NoteRepository {
    val AllNotes: LiveData<List<NoteModel>>
    suspend fun insertNote (noteModel: NoteModel, onSuccess:()->Unit)
    suspend fun deleteNote (noteModel: NoteModel,onSuccess:()->Unit)
    suspend fun updateNote (noteModel: NoteModel,onSuccess:()->Unit)
    suspend fun insertTask (tasks: Tasks,onSuccess:()->Unit)
    abstract fun insertNode(nodeWithTask: NodeWithTask,onSuccess: () -> Unit)
    val getTask: LiveData<List<NodeWithTask>>
    suspend fun getTask(id:Int,onSuccess: () -> Unit)
}