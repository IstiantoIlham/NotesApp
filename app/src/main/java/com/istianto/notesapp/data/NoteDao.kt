package com.istianto.notesapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.istianto.notesapp.data.models.NoteData

@Dao
interface NoteDao {
    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getDataAll() : LiveData<List<NoteData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertData(noteData : NoteData)

    @Update
    fun updateData(noteData : NoteData)

    @Delete
    fun deleteData(noteData : NoteData)

    @Query("DELETE FROM todo_table")
    fun deleteAllData()

    @Query("SELECT * FROM todo_table WHERE title LIKE :searchQuery")
    fun searchDatabase(searchQuery : String) : LiveData<List<NoteData>>

    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END")
    fun sortByHighPriority() : LiveData<List<NoteData>>

    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END")
    fun sortByLowPriority() : LiveData<List<NoteData>>

}