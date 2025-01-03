package com.example.lastthree.NoteApp.data

import androidx.room.Query

class NoteRepository(private val noteDao: NoteDao) {
    suspend fun insert(note: NoteEntity) = noteDao.insert(note)


    suspend fun getAllNodes() : List<NoteEntity> = noteDao.getAllNodes()
}