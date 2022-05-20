package com.example.myapplication.screen.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Repostory
import com.example.myapplication.db.NoteDataBase
import com.example.myapplication.db.repostirory.NoteRealisation
import com.example.myapplication.model.NodeWithTask
import com.example.myapplication.model.NoteModel
import com.example.myapplication.model.Tasks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StartViewModel(application: Application):AndroidViewModel(application) {
    fun delete (noteModel: NoteModel,onSuccess:()->Unit)=
        viewModelScope.launch (Dispatchers.IO) {
            Repostory.deleteNote(noteModel) {
                onSuccess()
            }
        }
    val context= application
    fun initDatabase(){
val daoNote =NoteDataBase.getInstance(context).getANoteDao()
        Repostory=NoteRealisation(daoNote)
    }
    fun getAllNotes():LiveData<List<NoteModel>>{
        return Repostory.AllNotes

    }
 fun getKast(int: Int,onSuccess: () -> Unit)=
     viewModelScope.launch ( Dispatchers.IO ){
         Repostory.getTask(int){
             onSuccess()
     }

}

    fun getUsersWithPlaylists(): LiveData<List<NodeWithTask>> {
        return Repostory.getTask
    }
    fun insert(tasks: Tasks, onSuccess:()->Unit)=
        viewModelScope.launch (Dispatchers.IO) {
            Repostory.insertTask(tasks){
                onSuccess()
            }
        }
}