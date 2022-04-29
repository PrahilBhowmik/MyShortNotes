package com.example.myshortnotes

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), NotesAdapt {

    private val viewModel: NotesViewModel by viewModels {
        NoteViewModelFactory((application as NotesApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = RVAdapter(this)
        recyclerView.adapter = adapter

        viewModel.allNotes.observe(this) {
            it?.let {
                adapter.updateList(it as ArrayList<Note>)
            }
        }

        val addBtn = findViewById<Button>(R.id.button)
        val input = findViewById<EditText>(R.id.NoteInput)

        addBtn.setOnClickListener{
            val s = input.text.toString()
            if(s.isNotEmpty())
            viewModel.insert(Note(s))
            Toast.makeText(this,"Inserted",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onItemClicked(note: Note) {
        viewModel.delete(note)
        Toast.makeText(this,"Deleted",Toast.LENGTH_SHORT).show()
    }
}