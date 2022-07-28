package com.example.myapplication.db.repostirory

import androidx.lifecycle.LiveData
import com.example.myapplication.db.NoteDao
import com.example.myapplication.model.FilesNote
import com.example.myapplication.model.Node
import com.example.myapplication.model.NodeWithTask
import com.example.myapplication.model.Tasks

 class NoteRealisation(private val noteDao: NoteDao):NoteRepository {
     override val AllNotes: LiveData<List<FilesNote>>
         get() = noteDao.getAllNotes()

     override fun getNode(id: Int): LiveData<List<Node>> {
         return noteDao.getNode(id)
     }


     override suspend fun insertNote(noteModel: FilesNote, onSuccess: () -> Unit) {
         noteDao.insert(noteModel)
         onSuccess()
     }

     override suspend fun updateNote(noteModel: FilesNote, onSuccess: () -> Unit) {
         noteDao.update(noteModel)
         onSuccess()
     }

     override suspend fun deleteNote(noteModel: FilesNote, onSuccess: () -> Unit) {
         noteDao.delete(noteModel)
         onSuccess()
     }


     override fun deleteAllTasks(id: Int, onSuccess: () -> Unit) {
         noteDao.deleteTaskUserById(id =id )
     }

     override fun deletAllNodes(id: Int, onSuccess: () -> Unit) {
        noteDao.deleteNodeUserById(id=id)
     }


     override suspend fun insertTask(tasks: Tasks, onSuccess: () -> Unit) {
         noteDao.insert(tasks)
         onSuccess()
     }

     override suspend fun insertNode(node: Node, onSuccess: () -> Unit) {
         noteDao.insert(node)
     }

     override suspend fun deleteNode(node: Node, onSuccess: () -> Unit) {
         noteDao.delete(node)
     }

     override suspend fun deleteTask(tasks: Tasks, onSuccess: () -> Unit) {
         noteDao.delete(tasks)

     }

     override suspend fun updateNode(node: Node, onSuccess: () -> Unit) {
         noteDao.updateNode(node)
     }


     override suspend fun updateTask(tasks: Tasks, onSuccess: () -> Unit) {
         noteDao.updateTask(tasks)
     }
     override fun list(id: Int):LiveData<List<Tasks>> {
         return noteDao.getTasks(ida = id)
      }





     override val getTask: LiveData<List<NodeWithTask>>
         get() = TODO("Not yet implemented")
 }