package com.example.myshortnotes

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class NotesRepository(private val notesDao: NotesDAO) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(note: Note) {
        notesDao.insert(note)
    }
    @WorkerThread
    suspend fun delete(note: Note) {
        notesDao.delete(note)
    }
}