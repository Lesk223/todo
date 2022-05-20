package com.example.myapplication.db.repostirory

import androidx.lifecycle.LiveData
import com.example.myapplication.db.NoteDao
import com.example.myapplication.model.NodeWithTask
import com.example.myapplication.model.NoteModel
import com.example.myapplication.model.Tasks
import org.w3c.dom.Node

class NoteRealisation(private val noteDao: NoteDao):NoteRepository {
    override val AllNotes: LiveData<List<NoteModel>>
        get()=noteDao.getAllNotes()
      override suspend fun getTask(id: Int, onSuccess: () -> Unit) {
        noteDao.getTasks(id)
    }
    override suspend fun insertNote(noteModel: NoteModel, onSuccess: () -> Unit) {
noteDao.insert(noteModel)
    onSuccess()
    }
    override suspend fun updateNote(noteModel: NoteModel, onSuccess: () -> Unit) {
        noteDao.update(noteModel)
        onSuccess()
    }

    override suspend fun deleteNote(noteModel: NoteModel, onSuccess: () -> Unit) {
        noteDao.delete(noteModel)
    onSuccess()
    }
    override suspend fun insertTask(tasks: Tasks, onSuccess: () -> Unit) {
        noteDao.insert(tasks)
        onSuccess()
    }

    override fun insertNode(nodeWithTask: NodeWithTask, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override val getTask: LiveData<List<NodeWithTask>>
        get() = TODO("Not yet implemented")


}