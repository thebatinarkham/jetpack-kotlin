package com.example.kotlin.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.example.kotlin.data.TodoDao
import com.example.kotlin.model.ToDoData
import com.example.kotlin.utils.NotificationHelper

class TodoRepository (private val todoDao: TodoDao){
    val getAllData:LiveData<List<ToDoData>> = todoDao.getAllData()
    val sortByHighPriority :LiveData<List<ToDoData>> = todoDao.sortByHighPriority()
    val sortByLowPriority :LiveData<List<ToDoData>> = todoDao.sortByLowPriority()

//    val list:LiveData<PagedList<ToDoData>> = todoDao.concertsByDate().toLiveData(pageSize = 20)

    suspend fun insertData(toDoData: ToDoData){
        todoDao.insertData(toDoData)
    }

    suspend fun updateData(toDoData: ToDoData){
        todoDao.updateData(toDoData)
    }

    suspend fun deleteItem(toDoData: ToDoData){
        todoDao.deleteItem(toDoData)
    }

    suspend fun deleteAll(){
        todoDao.deleteAll()
    }

    fun searchDatabase(searchQuery:String):LiveData<List<ToDoData>>{
        return todoDao.searchDatabase(searchQuery)
    }
}