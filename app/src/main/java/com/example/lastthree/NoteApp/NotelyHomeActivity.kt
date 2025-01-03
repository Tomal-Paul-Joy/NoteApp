package com.example.lastthree.NoteApp

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.lastthree.NoteApp.data.NoteEntity
import com.example.lastthree.R
import com.example.lastthree.databinding.ActivityNotelyHomeBinding

class NotelyHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotelyHomeBinding
    private val viewModel: NoteViewModel by viewModels(){
        NoteViewModel.getFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotelyHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.fabAddNode.setOnClickListener{
            val noteTitle = binding.etTitle.text.toString().trim()
            val noteDescription = binding.etDescription.text.toString().trim()
            if(noteDescription.isEmpty() || noteTitle.isEmpty()) {
                showMessage("Please insert note title & description")
            }
            else {
                viewModel.addNote(noteTitle,noteDescription)

            }
        }

        val noteObserver = Observer<List<NoteEntity>>{notes ->
            if(notes.isEmpty()) {
                binding.tvResult.text = "Add your first note"

            }else {
                binding.tvResult.text = notes.first().title
            }



        }
        viewModel.noteList.observe(this, noteObserver)
        viewModel.loadNotes()


    }
    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}