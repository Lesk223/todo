package com.example.myapplication.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import java.io.Serializable


@Entity(tableName = "note_table")
data class FilesNote (
    @PrimaryKey(autoGenerate = true)
    var id: Int=0,
    @ColumnInfo
    var title:String="",
    @ColumnInfo
    var description:String="",
    @ColumnInfo
     var time:String="",
    ) : Serializable
@Entity(tableName = "Node")
data class Node(
    @PrimaryKey(autoGenerate = true)
    var nodeId:Int=0,
    @ColumnInfo
    var name:String="",
    @ColumnInfo
    var description:String="",
    @ColumnInfo
    var date:String="",
    @ColumnInfo
    var category:String="",
    @ColumnInfo
    val ownerId:Int=0
) : Serializable

@Entity
data class Tasks(
    @PrimaryKey(autoGenerate = true)
    var taskId:Int=0,
    @ColumnInfo
    var name:String="",
@ColumnInfo
    var date:String="",
    @ColumnInfo
    var category:String="",
    @ColumnInfo
    var check:Boolean=false,
   @ColumnInfo
    val ownerId:Int=0
)
@Entity(tableName = "note_table",
    foreignKeys = [
        ForeignKey(entity = FilesNote::class,
            parentColumns = ["id"],
            childColumns = ["ownerId"],
            onDelete = CASCADE)])

data class NodeWithTask(
    @Embedded val filesNote: FilesNote,
    @Relation(
        parentColumn = "id",
        entityColumn = "ownerId"
    )
//    val tasks: Tasks,
    val nodeWithtask: List<Tasks>
)


