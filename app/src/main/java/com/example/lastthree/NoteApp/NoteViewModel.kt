package com.example.lastthree.NoteApp

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.lastthree.NoteApp.data.NoteDatabase
import com.example.lastthree.NoteApp.data.NoteEntity
import com.example.lastthree.NoteApp.data.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(private val noteRepository: NoteRepository): ViewModel() {

     private var _noteList = MutableLiveData<List<NoteEntity>>()

     val noteList: LiveData<List<NoteEntity>>
         get() = _noteList

    fun loadNotes() {
       viewModelScope.launch(Dispatchers.IO) {
           val notes = noteRepository.getAllNodes()
           _noteList.postValue(notes)
       }
    }

    fun addNote(title: String, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
           noteRepository.insert(NoteEntity(title =title, description = description))
            loadNotes()
        }
    }

    companion object{
        @Suppress("UNCHECKED_CAST")
        fun getFactory(context: Context): ViewModelProvider.Factory = object: ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {

                val dao = NoteDatabase.getDatabase(context).noteDao()
                val repository = NoteRepository(dao)
                return NoteViewModel(repository) as T
            }
        }

    }
}