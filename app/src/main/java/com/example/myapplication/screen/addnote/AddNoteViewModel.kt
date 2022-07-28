package com.example.myapplication.screen.addnote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Repostory
import com.example.myapplication.model.FilesNote
import com.example.myapplication.model.Node
import com.example.myapplication.model.Tasks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNoteViewModel: ViewModel() {
    fun update (node: Node,onSuccess:()->Unit)=
        viewModelScope.launch (Dispatchers.IO) {
            Repostory.updateNode(node) {
                onSuccess()
            }
        }
    fun delete (node: Node,onSuccess:()->Unit)=
        viewModelScope.launch (Dispatchers.IO) {
            Repostory.deleteNode(node) {
                onSuccess()
            }
        }
    fun insert(noteModel: FilesNote,onSuccess:()->Unit)=
        viewModelScope.launch (Dispatchers.IO) {
            Repostory.insertNote(noteModel){
                onSuccess()
            }
        }
    fun update (tasks: Tasks,onSuccess:()->Unit)=
        viewModelScope.launch (Dispatchers.IO) {
            Repostory.updateTask(tasks) {
                onSuccess()
            }
        }
    fun delete (noteModel: FilesNote,onSuccess:()->Unit)=
        viewModelScope.launch (Dispatchers.IO) {
            Repostory.deleteNote(noteModel) {
                onSuccess()
            }
        }
            fun update (noteModel: FilesNote,onSuccess:()->Unit)=
                viewModelScope.launch (Dispatchers.IO) {
                    Repostory.updateNote(noteModel){
                        onSuccess()
                    }
}}