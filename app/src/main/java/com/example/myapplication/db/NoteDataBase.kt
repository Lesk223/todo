package com.example.myapplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.model.FilesNote
import com.example.myapplication.model.Node
import com.example.myapplication.model.Tasks


@Database(entities =[FilesNote::class,Tasks::class, Node::class], version =2)
abstract class NoteDataBase: RoomDatabase() {
abstract fun  getANoteDao():NoteDao

companion object{
    private var database:NoteDataBase?=null
    @Synchronized

    fun getInstance(context:Context):NoteDataBase{
        return  if (database==null){
database= Room.databaseBuilder(context,NoteDataBase::class.java,"data")
    .addMigrations(MIGRATION_1_2)
    .build()
            database as NoteDataBase
        }else{
            database as NoteDataBase
        }
    }


    val MIGRATION_1_2: Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Create the new table
            database.execSQL(
                "CREATE TABLE Tasks (`taskId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `date` TEXT NOT NULL, `category` TEXT NOT NULL, `check` INTEGER NOT NULL, `ownerId` INTEGER NOT NULL)"
            )
            database.execSQL(
                "CREATE TABLE `Node` (`nodeId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `description` TEXT NOT NULL, `date` TEXT NOT NULL, `category` TEXT NOT NULL, `ownerId` INTEGER NOT NULL)"
            )
            database.execSQL(
                "INSERT INTO Node (nodeId, name, description,date,ownerId,category) SELECT id, title, description,time,1,'as' FROM note_table"
            )
            database.execSQL(
                "DELETE FROM note_table"
            )
            database.execSQL(
                "INSERT INTO note_table (id,title,description,time)\n" +
                        "VALUES (1, 'Ваши записи', 'sa',  8500.00 );"
            )
            // Copy the data

        }
    }}
}