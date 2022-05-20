package com.example.myapplication.model

import androidx.room.*
import java.io.Serializable

@Entity(tableName = "note_table")
data class NoteModel (
    @PrimaryKey(autoGenerate = true)
    var id: Int=0,
    @ColumnInfo
    var title:String="",
    @ColumnInfo
    var description:String="",
    @ColumnInfo
     var time:String="",
    ) : Serializable
@Entity
data class Tasks(
    @PrimaryKey
    var taskId:Int=0,
    @ColumnInfo
    var name:String="",
@ColumnInfo
    var date:String="",
    @ColumnInfo
    var category:String="",
   @ColumnInfo
    val ownerId:Int=0
)

data class NodeWithTask(
    @Embedded val noteModel: NoteModel,
    @Relation(

        parentColumn = "id",
        entityColumn = "ownerId"
    )
//    val tasks: Tasks,
    val nodeWithtask: List<Tasks>
)

