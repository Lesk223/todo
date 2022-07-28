package com.example.myapplication.db.repostirory

import android.app.TaskStackBuilder
import androidx.lifecycle.LiveData
import com.example.myapplication.db.NoteDao
import com.example.myapplication.model.FilesNote
import com.example.myapplication.model.Node
import com.example.myapplication.model.NodeWithTask
import com.example.myapplication.model.Tasks

interface NoteRepository {
    val AllNotes: LiveData<List<FilesNote>>
    fun getNode(id: Int):LiveData<List<Node>>
    suspend fun insertNote (noteModel: FilesNote, onSuccess:()->Unit)
    suspend fun deleteNote (noteModel: FilesNote,onSuccess:()->Unit)
    suspend fun updateNote (noteModel: FilesNote,onSuccess:()->Unit)
    suspend fun insertTask (tasks: Tasks,onSuccess:()->Unit)
    suspend fun insertNode (node: Node,onSuccess:()->Unit)
    suspend fun deleteNode (node: Node,onSuccess:()->Unit)
    suspend fun deleteTask (tasks: Tasks,onSuccess:()->Unit)
    suspend fun updateNode(node: Node,onSuccess: () -> Unit)
    suspend fun updateTask(tasks: Tasks,onSuccess: () -> Unit)
    fun list(id:Int): LiveData<List<Tasks>>
    val getTask: LiveData<List<NodeWithTask>>
    fun deleteAllTasks(id: Int, onSuccess: () -> Unit)
    fun deletAllNodes(id: Int,onSuccess: () -> Unit)
}