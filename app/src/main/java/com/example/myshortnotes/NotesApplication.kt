package com.example.myshortnotes

import android.app.Application

class NotesApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { NotesRoomDatabase.getDatabase(this) }
    val repository by lazy { NotesRepository(database.notesDao()) }
}