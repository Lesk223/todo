package com.example.myapplication.screen.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Repostory
import com.example.myapplication.db.NoteDataBase
import com.example.myapplication.db.repostirory.NoteRealisation
import com.example.myapplication.model.FilesNote
import com.example.myapplication.model.Node
import com.example.myapplication.model.NodeWithTask
import com.example.myapplication.model.Tasks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StartViewModel(application: Application):AndroidViewModel(application) {
    fun delete (noteModel: FilesNote,onSuccess:()->Unit)=
        viewModelScope.launch (Dispatchers.IO) {
            Repostory.deleteNote(noteModel) {
                onSuccess()
            }
        }

    fun deleteAllTasks (id:Int,onSuccess:()->Unit)=
        viewModelScope.launch (Dispatchers.IO) {
            Repostory.deleteAllTasks(id) {
                onSuccess()
            }
        }
    fun deleteAllNodes (id:Int,onSuccess:()->Unit)=
        viewModelScope.launch (Dispatchers.IO) {
            Repostory.deletAllNodes(id){
                onSuccess()
            }
        }
    val context= application
    fun initDatabase(){
val daoNote =NoteDataBase.getInstance(context).getANoteDao()
        Repostory=NoteRealisation(daoNote)
    }
    fun getAllNotes():LiveData<List<FilesNote>> {
        return Repostory.AllNotes
    }

 fun getAllTask(int: Int): LiveData<List<Tasks>> {
    return Repostory.list(int)
}
    fun getAllNode(int: Int): LiveData<List<Node>> {
        return Repostory.getNode(int)
    }
    fun getUsersWithPlaylists(): LiveData<List<NodeWithTask>> {
        return Repostory.getTask
    }

    fun delete (tasks: Tasks,onSuccess:()->Unit)=
        viewModelScope.launch (Dispatchers.IO) {
            Repostory.deleteTask(tasks) {
                onSuccess()
            }
        }




    fun insert(tasks: Tasks, onSuccess:()->Unit)=
        viewModelScope.launch (Dispatchers.IO) {
            Repostory.insertTask(tasks){
                onSuccess()
            }
        }
    fun insert(node: Node, onSuccess:()->Unit)=
        viewModelScope.launch (Dispatchers.IO) {
            Repostory.insertNode(node){
                onSuccess()
            }
        }
}