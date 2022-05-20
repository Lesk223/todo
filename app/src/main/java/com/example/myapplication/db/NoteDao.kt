package com.example.myapplication.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.model.NodeWithTask
import com.example.myapplication.model.NoteModel
import com.example.myapplication.model.Tasks
import org.w3c.dom.Node
import java.lang.StringBuilder

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(noteModel: NoteModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Tasks)

    @Update
    suspend fun update(noteModel: NoteModel)

    @Delete
    suspend fun delete(noteModel: NoteModel)

    @Query("SELECT * from note_table")
    fun getAllNotes(): LiveData<List<NoteModel>>


    @Transaction
    @Query("SELECT * from TASKS WHERE ownerId=:ida")
    fun getTasks(ida:Int): List<Tasks>
    @Transaction
    @Query("SELECT * from note_table")
    fun getUsersWithPlaylists(): LiveData<List<NodeWithTask>>
    //abstract fun getTasks(): LiveData<List<Tasks>>
}