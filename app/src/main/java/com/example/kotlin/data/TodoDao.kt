package com.example.kotlin.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.kotlin.model.ToDoData
import java.lang.StringBuilder

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllData():LiveData<List<ToDoData>>



    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(toDoData: ToDoData)//suspend tell compiler our function will run inside
    // coroutine

    @Update
    suspend fun updateData(toDoData: ToDoData)
    
    @Delete
    suspend fun deleteItem(toDoData: ToDoData)

    @Query("DELETE FROM todo_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM todo_table WHERE title LIKE :searchQuery")
    fun searchDatabase(searchQuery: String):LiveData<List<ToDoData>>

    @Query("SELECT * FROM todo_table ORDER BY CASE  WHEN  priority LIKE 'H%'  THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END")
    fun sortByHighPriority():LiveData<List<ToDoData>>

    @Query("SELECT * FROM todo_table ORDER BY CASE  WHEN  priority LIKE 'L%'  THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END")
    fun sortByLowPriority():LiveData<List<ToDoData>>


//    @Query("SELECT * FROM todo_table ORDER BY id ASC")
//    fun concertsByDate(): DataSource.Factory<Int, ToDoData>
}