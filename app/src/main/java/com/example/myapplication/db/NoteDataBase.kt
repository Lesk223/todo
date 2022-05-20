package com.example.myapplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.model.NodeWithTask
import com.example.myapplication.model.NoteModel
import com.example.myapplication.model.Tasks

@Database(entities =[NoteModel::class,Tasks::class], version =1)
abstract class NoteDataBase: RoomDatabase() {
abstract fun  getANoteDao():NoteDao

companion object{
    private var database:NoteDataBase?=null
    @Synchronized

    fun getInstance(context:Context):NoteDataBase{
        return  if (database==null){
database= Room.databaseBuilder(context,NoteDataBase::class.java,"base").build()
            database as NoteDataBase
        }else{
            database as NoteDataBase
        }
    }
}
}