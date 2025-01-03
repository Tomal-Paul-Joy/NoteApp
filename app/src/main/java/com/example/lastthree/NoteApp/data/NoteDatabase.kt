package com.example.lastthree.NoteApp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class] , version = 1)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun noteDao(): NoteDao

    companion object{
        private var INSTANCE: NoteDatabase?=null
        fun getDatabase(context: Context): NoteDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context = context.applicationContext,
                    NoteDatabase::class.java,
                    "note database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}