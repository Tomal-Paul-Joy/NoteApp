package com.example.lastthree.NoteApp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {
       @Insert
       suspend fun insert(note: NoteEntity)

       @Query("SELECT * FROM notes ORDER BY id DESC")
       suspend fun getAllNodes() : List<NoteEntity>

}