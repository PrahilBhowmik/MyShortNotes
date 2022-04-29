package com.example.myshortnotes

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NotesDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Select * from NotesTable order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>
}