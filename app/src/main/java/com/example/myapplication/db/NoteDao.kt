package com.example.myapplication.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.model.FilesNote
import com.example.myapplication.model.Node
import com.example.myapplication.model.Tasks

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(noteModel: FilesNote)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Tasks)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(node: Node)

    @Update
    suspend fun updateNode(node: Node)
    @Update
    suspend fun update(noteModel: FilesNote)
    @Update
    suspend fun updateTask(task: Tasks)
    @Delete
    suspend fun delete(task: Tasks)
    @Delete
    suspend fun delete(node: Node)
    @Delete
    suspend fun delete(noteModel: FilesNote)

    @Query("DELETE FROM tasks WHERE ownerId=:id")
    fun deleteTaskUserById(id: Int)
    @Query("DELETE FROM Node WHERE ownerId=:id")
    fun deleteNodeUserById(id: Int)

    @Query("SELECT * from note_table")
    fun getAllNotes(): LiveData<List<FilesNote>>

    @Transaction
    @Query("SELECT * from TASKS WHERE ownerId=:ida")
    fun getTasks(ida:Int):LiveData<List<Tasks>>
    @Transaction
    @Query("SELECT * from Node WHERE ownerId=:ida")
    fun getNode(ida:Int):LiveData<List<Node>>


    //abstract fun getTasks(): LiveData<List<Task
    //abstract fun getTasks(): LiveData<List<Tasks>>




}