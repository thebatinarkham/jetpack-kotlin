package com.example.kotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlin.data.TodoDatabase
import com.example.kotlin.model.ToDoData
import com.example.kotlin.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application):AndroidViewModel(application) {
    private val todoDao = TodoDatabase.getDatabase(
        application
    ).todoDao()

    private val repository:TodoRepository
    val  getAllData:LiveData<List<ToDoData>>
    val sortByHighPriority :LiveData<List<ToDoData>>
    val sortByLowPriority :LiveData<List<ToDoData>>

    init{
        repository = TodoRepository(todoDao)
        getAllData = repository.getAllData
        sortByLowPriority = repository.sortByLowPriority
        sortByHighPriority = repository.sortByHighPriority
    }

    fun insertData(toDoData: ToDoData){
        viewModelScope.launch ( Dispatchers.IO){
            repository.insertData(toDoData)

        }
    }

    fun updateData(toDoData: ToDoData){
        viewModelScope.launch (Dispatchers.IO){
            repository.updateData(toDoData)
        }

    }


    fun deleteItem(toDoData: ToDoData){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteItem(toDoData)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAll()
        }
    }

    fun searchDatabase(searchQuery:String):LiveData<List<ToDoData>>{
        return repository.searchDatabase(searchQuery)
    }
}